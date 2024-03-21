package com.zolobooky.booky;

import com.zolobooky.booky.books.BookController;
import com.zolobooky.booky.books.BookService;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookServiceTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	private final BookAPITestAssets bookAPITestAssets = new BookAPITestAssets();

	@Test
	void fetchBooksServiceTest() throws Exception {
		when(bookService.getBooks(0, 5)).thenReturn(bookAPITestAssets.getBooks());
		this.mockMvc.perform(get("/v0/books")).andExpect(status().isOk());
	}

	@Test
	void fetchBookByIDServiceTest() throws Exception {
		when(bookService.getBookById(0)).thenReturn(bookAPITestAssets.getBookById());
		this.mockMvc.perform(get("/v0/books/0")).andExpect(status().isOk());
	}

	//
	@Test
	void delistBookSerivceTest() throws Exception {
		when(bookService.deleteBook(0)).thenReturn(bookAPITestAssets.getDelistedBook());
		this.mockMvc.perform(delete("/v0/books/0")).andExpect(status().isOk());
	}

}
