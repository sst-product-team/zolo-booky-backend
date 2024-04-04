package com.zolobooky.booky.books.dto;

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

	String author;

	BookStatus status;

	Integer maxBorrow;

	Integer requestCount;

	String thumbnail;

	UserEntity owner;

}
