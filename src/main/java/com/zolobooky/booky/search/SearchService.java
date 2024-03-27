package com.zolobooky.booky.search;

import com.zolobooky.booky.books.BookEntity;
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
		return this.bookRepository.findAllByNameContainsIgnoreCaseAndAuthorContainsIgnoreCase(name, author);
	}

}
