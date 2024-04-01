package com.zolobooky.booky;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zolobooky.booky.books.BookController;
import com.zolobooky.booky.books.BookService;
import static org.mockito.Mockito.when;

import com.zolobooky.booky.books.dto.CreateBookDTO;
import jdk.jfr.ContentType;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookServiceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	@Autowired
	private ObjectMapper objectMapper;

	private final BookAPITestAssets bookAPITestAssets = new BookAPITestAssets();

	@Test
	void fetchBooksServiceTest() throws Exception {
		when(bookService.getBooks(0, 5, -1)).thenReturn(bookAPITestAssets.getBooks());
		this.mockMvc.perform(get("/v0/books")).andExpect(status().isOk());
	}

	@Test
	void fetchBookByIDServiceTest() throws Exception {
		when(bookService.getBookById(0)).thenReturn(bookAPITestAssets.getBookById());
		this.mockMvc.perform(get("/v0/books/0")).andExpect(status().isOk());
	}

	@Test
	void createBookTest() throws Exception {

		System.out.println(bookAPITestAssets.getCreateBookDTO().toString());

		when(bookService.createBook(bookAPITestAssets.getCreateBookDTO())).thenReturn(bookAPITestAssets.book2);

		String jsonContent = objectMapper.writeValueAsString(bookAPITestAssets.getCreateBookDTO());

		this.mockMvc.perform(post("/v0/books").contentType(MediaType.APPLICATION_JSON).content(jsonContent))
			.andExpect(status().isOk());
	}

	@Test
	void delistBookSerivceTest() throws Exception {
		when(bookService.deleteBook(0)).thenReturn(bookAPITestAssets.getDelistedBook());
		this.mockMvc.perform(delete("/v0/books/0")).andExpect(status().isOk());
	}

}
