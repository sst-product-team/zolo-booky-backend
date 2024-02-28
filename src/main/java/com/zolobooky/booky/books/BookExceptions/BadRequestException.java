package com.zolobooky.booky.books.BookExceptions;

public class BadRequestException extends RuntimeException {

	public BadRequestException(String message) {
		super(message);
	}

}
