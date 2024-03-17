package com.zolobooky.booky.books.dto;

import java.sql.Date;

import com.zolobooky.booky.commons.CustomStatus.BookStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

	Integer id;

	String name;

	String author;

	String description;

	BookStatus status;

	Date availability;

	Double rating;

	String thumbnail;

	Integer owner;

}
