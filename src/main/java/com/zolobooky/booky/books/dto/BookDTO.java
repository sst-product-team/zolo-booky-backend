package com.zolobooky.booky.books.dto;

import java.sql.Date;

import com.zolobooky.booky.commons.CustomStatus.BookStatus;

import com.zolobooky.booky.users.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

	Integer id;

	String name;

	String description;

	BookStatus status;

	Date availability;

	Double rating;

	String thumbnail;

	UserEntity owner;

}
