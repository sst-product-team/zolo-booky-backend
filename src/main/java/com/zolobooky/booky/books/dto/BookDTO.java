package com.zolobooky.booky.books.dto;

import java.sql.Date;

import com.zolobooky.booky.commons.CustomStatus.BOOK_STATUS;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

	Integer book_id;

	String book_name;

	String book_desc;

	BOOK_STATUS book_status;

	Date book_next_available;

	Double book_rating;

	byte[] book_thumbnail;

	Integer owner_id;

}
