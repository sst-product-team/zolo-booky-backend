package com.zolobooky.booky.search;

import com.zolobooky.booky.books.BookEntity;
import com.zolobooky.booky.books.BookExceptions.BookNotFoundException;
import com.zolobooky.booky.books.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SearchService {

	private final BookRepository bookRepository;

	public SearchService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public List<BookEntity> searchBooks(String name, String author) {
		List<BookEntity> books = this.bookRepository.findAllByNameContainsIgnoreCaseAndAuthorContainsIgnoreCase(name,
				author);

		if (books.isEmpty()) {
			log.info(String.format("no book matching with search query name : %s & author: %s found.", name, author));
			throw new BookNotFoundException("no book matching with search query found.");
		}
		log.info(String.format(" %s books matching search query name : %s & author: %s found.", books.size(), name,
				author));
		return books;
	}

}
