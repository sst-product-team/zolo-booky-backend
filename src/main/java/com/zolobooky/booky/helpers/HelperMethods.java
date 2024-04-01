package com.zolobooky.booky.helpers;

import com.zolobooky.booky.books.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class HelperMethods {

	public static Page<BookEntity> convertListToPage(List<BookEntity> books, Pageable pageable) {
		return new PageImpl<>(books, pageable, books.size());
	}

}
