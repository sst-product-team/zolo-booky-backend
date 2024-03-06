package com.zolobooky.booky;

import com.zolobooky.booky.books.BookEntity;
import com.zolobooky.booky.books.BookRepository;
import com.zolobooky.booky.books.BookService;
import com.zolobooky.booky.books.dto.CreateBookDTO;
import com.zolobooky.booky.books.dto.UpdateBookDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.awt.print.Book;
import java.sql.Date;
import java.util.Arrays;

@SpringBootTest
public class BookServiceTest {

	//
	// @Autowired
	// private BookService bookService;
	//
	// @MockBean
	// private BookRepository bookRepository;
	//
	// private BookEntity book1 = new BookEntity();
	// private BookEntity book2 = new BookEntity();
	//
	//
	// private BookEntity createdBook;
	//
	// @Test
	// void fetchBooksServiceTest() {
	//
	//
	// Page<BookEntity> bookEntityPage = bookService.getBooks(0,10);
	// if (bookEntityPage != null) {
	// // Test Passes
	// } else {
	// // Test Failed
	// }
	// }
	//
	// @Test
	// void createBookServiceTest() {
	// CreateBookDTO createBookDTO = new CreateBookDTO();
	// createBookDTO.setName("Testing Book Name Creation");
	// createBookDTO.setDescription("Testing Book Description Creation");
	// createBookDTO.setAvailability(new Date(2024, 03, 10));
	// createBookDTO.setOwner(0);
	//
	// BookEntity book = bookService.createBook(createBookDTO);
	// if(book != null) {
	// createdBook = book;
	// // Test Passed
	// } else {
	// // Test Failed
	// }
	// }
	//
	// @Test
	// void fetchBookByIDServiceTest() {
	// int testBookID = createdBook.getId();
	// BookEntity book = bookService.getBookById(testBookID);
	//
	// if(book.getName() != createdBook.getName() || book.getAvailability() !=
	// createdBook.getAvailability() || book.getOwner() != createdBook.getOwner() ||
	// book.getStatus() != createdBook.getStatus()) {
	// // Test Failed
	// } else {
	// // Test Passed
	// }
	// }
	//
	//
	// @Test
	// void updateBookSerivceTest() {
	// int bookToUpdate = createdBook.getId();
	// String newName = "Updated Testing Book";
	//
	// UpdateBookDTO updateBookDTO = new UpdateBookDTO();
	// updateBookDTO.setName(newName);
	// BookEntity book = bookService.updateBook(updateBookDTO, bookToUpdate);
	// if(book.getName() != newName) {
	// // Test Failed
	// } else {
	// // Test Passed
	// }
	//
	// String newDescription = "Testing update book description";
	// updateBookDTO.setDescription(newDescription);
	//
	// book = bookService.updateBook(updateBookDTO, bookToUpdate);
	// if(book.getDescription() != newDescription) {
	// // Test Failed
	// } else {
	// // Test Passed
	// }
	//
	// Date newAvailability = new Date(2024, 4, 10);
	// updateBookDTO.setAvailability(newAvailability);
	//
	// book = bookService.updateBook(updateBookDTO, bookToUpdate);
	// if(book.getAvailability() != newAvailability) {
	// // Test Failed
	// } else {
	// // Test Passed
	// }
	// }
	//
	// @Test
	// void delistBookSerivceTest() {
	// int testBookID = createdBook.getId();
	// BookEntity book = bookService.deleteBook(testBookID);
	//
	// if(book.getName() != createdBook.getName() || book.getAvailability() !=
	// createdBook.getAvailability() || book.getOwner() != createdBook.getOwner() ||
	// book.getStatus() != createdBook.getStatus()) {
	// // Test Failed
	// } else {
	// // Test Passed
	// }
	// }

}
