package com.zolobooky.booky.books;

import com.zolobooky.booky.books.dto.BookDTO;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v0/books")
public class BookController {

	private final BookService bookService;

	private final ModelMapper modelMapper;

	public BookController(BookService bookService, ModelMapper modelMapper) {
		this.bookService = bookService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("")
	public ResponseEntity<List<BookDTO>> getBooks(@RequestParam(value = "page", defaultValue = "0") String pageString,
			@RequestParam(value = "size", defaultValue = "5") String sizeString) {
		Integer page = Integer.parseInt(pageString);
		Integer size = Integer.parseInt(sizeString);

		Page<BookEntity> books = this.bookService.getBooks(page, size);

		var response = books.stream().map((book) -> this.modelMapper.map(book, BookDTO.class)).toList();

		return ResponseEntity.ok(response);
	}

	@PostMapping("")
	public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDto) {
		var book = this.bookService.createBook(bookDto);
		var response = this.modelMapper.map(book, BookDTO.class);

		return ResponseEntity.ok(response);
	}

	@ExceptionHandler(BookService.BookNotFoundException.class)
	ResponseEntity<String> handleArticleNotFoundException(BookService.BookNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

}
