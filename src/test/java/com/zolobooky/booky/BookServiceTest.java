package com.zolobooky.booky;

import com.zolobooky.booky.books.BookController;
import com.zolobooky.booky.books.BookEntity;
import com.zolobooky.booky.books.BookService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.zolobooky.booky.books.dto.CreateBookDTO;
import com.zolobooky.booky.books.dto.UpdateBookDTO;
import com.zolobooky.booky.commons.CustomStatus;
import org.junit.jupiter.api.Test;
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
	void delistBookSerivceTest() throws Exception {
		when(bookService.deleteBook(0)).thenReturn(bookAPITestAssets.getDelistedBook());
		this.mockMvc.perform(delete("/v0/books/0")).andExpect(status().isOk());
	}

	@Test
	void createBookTest() throws Exception {
		CreateBookDTO createBookDTO = new CreateBookDTO();
		createBookDTO.setName("Demo Book DTO");
		createBookDTO.setAuthor("Demo User");
		createBookDTO.setDescription("This is a Demo Book");
		createBookDTO.setMaxBorrow(1);
		createBookDTO.setThumbnail("https://demo.book.url/");
		createBookDTO.setOwner(1);

		BookEntity bookEntity = bookAPITestAssets.createBook();
		when(bookService.createBook(any(createBookDTO.getClass()))).thenReturn(bookEntity);

		String payload = bookAPITestAssets.toJSONString(createBookDTO);

		mockMvc.perform(post("/v0/books").contentType(MediaType.APPLICATION_JSON).content(payload))
			.andExpect(status().isOk());
	}

	@Test
	void updateBookTest() throws Exception {
		UpdateBookDTO updateBookDTO = new UpdateBookDTO();
		updateBookDTO.setName("Demo Book DTO");
		updateBookDTO.setAuthor("Demo User");
		updateBookDTO.setDescription("This is a Demo Book");
		updateBookDTO.setMaxBorrow(1);
		updateBookDTO.setThumbnail("https://demo.book.url/");

		BookEntity bookEntity = bookAPITestAssets.updateBook();
		when(bookService.updateBook(any(updateBookDTO.getClass()), any(Integer.class))).thenReturn(bookEntity);

		String payload = bookAPITestAssets.toJSONString(updateBookDTO);

		mockMvc.perform(patch("/v0/books/1").contentType(MediaType.APPLICATION_JSON).content(payload))
			.andExpect(status().isOk());
	}

	// @Test
	void changeBookStatusTest() throws Exception {
		BookEntity bookEntity = bookAPITestAssets.updateBookStatus();

		when(bookService.updateStatus(any(Integer.class), any(CustomStatus.BookStatus.class))).thenReturn(bookEntity);
		mockMvc.perform(put("/v0/books/1").contentType(MediaType.APPLICATION_JSON).content(""))
			.andExpect(status().isOk());
	}

}
