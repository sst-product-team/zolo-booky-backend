package com.zolobooky.booky.books;

import com.zolobooky.booky.books.dto.BookDTO;
import com.zolobooky.booky.books.dto.CreateBookDTO;
import com.zolobooky.booky.books.dto.ListBookDTO;
import com.zolobooky.booky.books.dto.UpdateBookDTO;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<List<ListBookDTO>> getBooks(
			@RequestParam(value = "page", defaultValue = "0") String pageString,
			@RequestParam(value = "size", defaultValue = "5") String sizeString) {
		Integer page = Integer.parseInt(pageString);
		Integer size = Integer.parseInt(sizeString)	;

		Page<BookEntity> books = this.bookService.getBooks(page, size);

		var response = books.stream().map((book) -> this.modelMapper.map(book, ListBookDTO.class)).toList();

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id) {
		var book = this.bookService.getBookById(id);
		var response = this.modelMapper.map(book, BookDTO.class);

		return ResponseEntity.ok(response);
	}

	@PostMapping("")
	public ResponseEntity<BookDTO> createBook(@RequestBody CreateBookDTO createBookDto) {
		var book = this.bookService.createBook(createBookDto);
		var response = this.modelMapper.map(book, BookDTO.class);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<BookDTO> deleteBook(@PathVariable Integer id) {
		var deletedBook = this.bookService.deleteBook(id);
		var response = this.modelMapper.map(deletedBook, BookDTO.class);

		return ResponseEntity.ok(response);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<BookDTO> updateBook(@RequestBody UpdateBookDTO updateBookDTO, @PathVariable Integer id) {
		var updatedBook = this.bookService.updateBook(updateBookDTO, id);
		var response = this.modelMapper.map(updatedBook, BookDTO.class);

		return ResponseEntity.ok(response);
	}

}
