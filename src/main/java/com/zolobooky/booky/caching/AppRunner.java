package com.zolobooky.booky.caching;

import com.zolobooky.booky.books.BookService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;

public class AppRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

	private final BookService bookService;

	public AppRunner(BookService bookService) {
		this.bookService = bookService;
	}

	public void run(String... args) throws Exception {
		logger.info(".... Fetching books");
		logger.info("isbn-1234 -->" + bookService.getBooks(0, 5));
	}

}
