package com.zolobooky.booky.commons;

import com.zolobooky.booky.appeals.AppealExceptions.AppealAlreadyExistsException;
import com.zolobooky.booky.images.ImageExceptions.ImageNotFoundExceptions;
import com.zolobooky.booky.notifications.FireExceptions.FireSendingError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zolobooky.booky.books.BookExceptions.BadRequestException;
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

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ExceptionResponse> bookBadRequest(BadRequestException badRequestException) {
		ExceptionResponse resp = new ExceptionResponse();

		resp.setMessage(badRequestException.getMessage());
		resp.setStatusCode("" + HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AppealAlreadyExistsException.class)
	public ResponseEntity<ExceptionResponse> appealAlreadyExists(
			AppealAlreadyExistsException appealAlreadyExistsException) {
		ExceptionResponse resp = new ExceptionResponse();

		resp.setMessage(appealAlreadyExistsException.getMessage());
		resp.setStatusCode("" + HttpStatus.CONFLICT);

		return new ResponseEntity<>(resp, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(ImageNotFoundExceptions.class)
	public ResponseEntity<ExceptionResponse> imageNotFound(ImageNotFoundExceptions imageNotFoundExceptions) {
		ExceptionResponse resp = new ExceptionResponse();

		resp.setMessage(imageNotFoundExceptions.getMessage());
		resp.setStatusCode("" + HttpStatus.NOT_FOUND);

		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(FireSendingError.class)
	public ResponseEntity<ExceptionResponse> fireSendingError(FireSendingError fireSendingError) {
		ExceptionResponse resp = new ExceptionResponse();

		resp.setMessage(fireSendingError.getMessage());
		resp.setStatusCode("" + HttpStatus.SERVICE_UNAVAILABLE);

		return new ResponseEntity<>(resp, HttpStatus.SERVICE_UNAVAILABLE);
	}

}
