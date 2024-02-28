package com.zolobooky.booky.books.BookExceptions;

public class BookNotFoundException extends RuntimeException {

	public BookNotFoundException(String message) {
		super(message);
	}

}
