package com.zolobooky.booky.books.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CreateBookDTO {

	@NonNull
	String name;

	String author;

	String description;

	@NonNull
	Integer maxBorrow;

	String thumbnail;

	@NonNull
	Integer owner;

}
