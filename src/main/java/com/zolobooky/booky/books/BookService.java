package com.zolobooky.booky.books;

import com.zolobooky.booky.books.dto.BookDTO;
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

	public BookEntity createBook(BookDTO bookDto) {
		BookEntity book = this.modelMapper.map(bookDto, BookEntity.class);

		var savedBook = this.bookRepository.save(book);

		return savedBook;
	}

	public static class BookNotFoundException extends IllegalArgumentException {

		public BookNotFoundException(Integer id) {
			super("Book with book_id: " + id + " not found");
		}

	}

}
