package com.zolobooky.booky;

import com.zolobooky.booky.books.BookEntity;
import com.zolobooky.booky.commons.CustomStatus;
import com.zolobooky.booky.users.UserEntity;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.List;

@SpringBootTest
public class BookAPITestAssets {

	private final List<BookEntity> books;

	public BookEntity book1;

	public BookEntity book2;

	public static <T> Page<T> convertListToPage(List<T> list, int page, int size) {
		int start = Math.min(page * size, list.size());
		int end = Math.min((start + size), list.size());

		return new PageImpl<>(list.subList(start, end), PageRequest.of(page, size), list.size());
	}

	public BookAPITestAssets() {

		UserEntity user1 = new UserEntity();
		user1.setId(99);
		user1.setName("Kushagra");
		user1.setFcmToken("KUSHAGRA-FCM-TOKEN");

		book1 = new BookEntity();
		book1.setId(0);
		book1.setName("Test Book 1");
		book1.setStatus(CustomStatus.BookStatus.AVAILABLE);
		book1.setOwner(user1);
		book1.setThumbnail("TEST IMAGE URL");

		book2 = new BookEntity();
		book2.setId(343834);
		book2.setName("Test Book 2");
		book2.setStatus(CustomStatus.BookStatus.AVAILABLE);
		book2.setOwner(user1);
		book2.setThumbnail("TEST IMAGE URL");

		books = List.of(book1, book2);
	}

	public Page<BookEntity> getBooks() {
		return convertListToPage(books, 0, 5);
	}

	public BookEntity getBookById() {
		return book2;
	}

	public BookEntity getDelistedBook() {
		book1.setStatus(CustomStatus.BookStatus.DELISTED);
		return book1;
	}

}
