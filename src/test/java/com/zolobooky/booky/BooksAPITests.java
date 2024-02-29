package com.zolobooky.booky;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zolobooky.booky.books.dto.CreateBookDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.sql.Date;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class BooksAPITests {

//	@Autowired
//	private MockMvc mockMvc;
//
//	@Autowired
//	private ObjectMapper objectMapper;
//
//	private void testSuccessPOSTRequest(String endpoint, String jsonModel) throws Exception {
//		mockMvc
//			.perform(post(endpoint).contentType(MediaType.APPLICATION_JSON)
//				.content(jsonModel)
//				.accept(MediaType.APPLICATION_JSON))
//			.andExpect(status().isOk());
//	}
//
//	private void testFailurePOSTRequest(String endpoint, String jsonModel, int ec) throws Exception {
//		if (ec == 400)
//			mockMvc
//				.perform(post(endpoint).contentType(MediaType.APPLICATION_JSON)
//					.content(jsonModel)
//					.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().is4xxClientError());
//		else if (ec == 500)
//			mockMvc
//				.perform(post(endpoint).contentType(MediaType.APPLICATION_JSON)
//					.content(jsonModel)
//					.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().is5xxServerError());
//		else
//			mockMvc
//				.perform(post(endpoint).contentType(MediaType.APPLICATION_JSON)
//					.content(jsonModel)
//					.accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().is(ec));
//	}
//
//	private void testSuccessGETRequest(String endpoint) throws Exception {
//		mockMvc.perform((get(endpoint))).andExpect(status().isOk());
//	}
//
//	private void testFailureGETRequest(String endpoint, int ec) throws Exception {
//		if (ec == 400)
//			mockMvc.perform((get(endpoint))).andExpect(status().is4xxClientError());
//		else if (ec == 500)
//			mockMvc.perform((get(endpoint))).andExpect(status().is5xxServerError());
//		else
//			mockMvc.perform((get(endpoint))).andExpect(status().is(ec));
//	}
//
//	@Test
//	void checkAwake() throws Exception {
//		testSuccessGETRequest("");
//	}
//
//	@Test
//	void fetchBooksTest() throws Exception {
//		// Fetch all books endpoint test
//		testSuccessGETRequest("/v0/books");
//
//		// Fetch a book with ID book ID which does not exist in the DB
//		int bookID = 0;
//		testFailureGETRequest("/v0/books/" + bookID, 404);
//	}
//
//	@Test
//	@Transactional
//	void createBook() throws Exception {
//
//		CreateBookDTO createBookDTO = new CreateBookDTO();
//
//		// Test create book with all data available
//		createBookDTO.setName("Test Book");
//		createBookDTO.setDescription("Test Book Description");
//		createBookDTO.setAvailability(new Date(1708905600L));
//		createBookDTO.setOwner(1);
//		String jsonModel = objectMapper.writeValueAsString(createBookDTO);
//		testSuccessPOSTRequest("/v0/books", jsonModel);
//
//	}
//
//	@Test
//	void emptyCreate() throws Exception {
//		int bookID = 0;
//		CreateBookDTO createBookDTO = new CreateBookDTO();
//		// Empty book data test
//		String jsonModel = objectMapper.writeValueAsString(createBookDTO);
//		mockMvc.perform(post("/v0/books").contentType(MediaType.APPLICATION_JSON).content(jsonModel))
//			.andExpect(status().is4xxClientError());
//	}

}