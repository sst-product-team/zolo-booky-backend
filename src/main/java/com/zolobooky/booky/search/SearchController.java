package com.zolobooky.booky.search;

import com.zolobooky.booky.books.BookEntity;
import com.zolobooky.booky.books.dto.ListBookDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v0/search")
public class SearchController {

	private final SearchService searchService;

	private final ModelMapper modelMapper;

	public SearchController(SearchService searchService, ModelMapper modelMapper) {
		this.searchService = searchService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/books")
	public ResponseEntity<List<ListBookDTO>> searchBooks(
			@RequestParam(value = "name", defaultValue = "") String bookName,
			@RequestParam(value = "author", defaultValue = "") String authorName) {
		List<BookEntity> filteredBooks = searchService.searchBooks(bookName, authorName);
		List<ListBookDTO> bookList = filteredBooks.stream()
			.map(bookEntity -> modelMapper.map(bookEntity, ListBookDTO.class))
			.collect(Collectors.toList());

		return ResponseEntity.ok(bookList);
	}

}
