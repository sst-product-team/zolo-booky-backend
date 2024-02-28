package com.zolobooky.booky.books;

import com.zolobooky.booky.books.BookExceptions.BookAlreadyExistsException;
import com.zolobooky.booky.books.BookExceptions.BookNotFoundException;
import com.zolobooky.booky.books.dto.CreateBookDTO;
import com.zolobooky.booky.commons.CustomStatus.BookStatus;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	private final BookRepository bookRepository;

	private final ModelMapper modelMapper;

	public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
		this.bookRepository = bookRepository;
		this.modelMapper = modelMapper;
	}

	public Page<BookEntity> getBooks(Integer page, Integer size) {
		return this.bookRepository.findAll(PageRequest.of(page, size));
	}

	public BookEntity getBookById(Integer id) {
		BookEntity book;
		if (this.bookRepository.findById(id).isEmpty()) {

			throw new BookNotFoundException(String.format("Book with Book ID: %s Not Found", id));
		}
		else {
			book = this.bookRepository.findById(id).get();
		}

		return book;
	}

	public BookEntity createBook(CreateBookDTO createBookDTO) {

		List<BookEntity> books = this.bookRepository.findByStatusOrderByName(BookStatus.AVAILABLE);
		BookEntity newBookToSave = this.modelMapper.map(createBookDTO, BookEntity.class);

		for (var book : books) {
			if (book.getName().equals(newBookToSave.getName()) && book.getOwner().equals(newBookToSave.getOwner())) {
				throw new BookAlreadyExistsException(
						String.format("book with NAME: %s already exists with ID: %d and is available to borrow.",
								book.getName(), book.getId()));
			}
		}
		return this.bookRepository.save(newBookToSave);
	}

}
