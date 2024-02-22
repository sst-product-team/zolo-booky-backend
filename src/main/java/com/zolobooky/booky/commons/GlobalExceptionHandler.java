package com.zolobooky.booky.commons;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zolobooky.booky.books.BookExceptions.BookAlreadyExistsException;
import com.zolobooky.booky.books.BookExceptions.BookNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ExceptionResponse> bookNotFound(BookNotFoundException bookNotFoundException) {
		ExceptionResponse resp = new ExceptionResponse();
		resp.setMessage(bookNotFoundException.getMessage());
		resp.setStatusCode("" + HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BookAlreadyExistsException.class)
	public ResponseEntity<ExceptionResponse> bookAlreadyExists(BookAlreadyExistsException bookAlreadyExistsException) {
		ExceptionResponse resp = new ExceptionResponse();

		resp.setMessage(bookAlreadyExistsException.getMessage());
		resp.setStatusCode("" + HttpStatus.CONFLICT);

		return new ResponseEntity<>(resp, HttpStatus.CONFLICT);
	}

}
