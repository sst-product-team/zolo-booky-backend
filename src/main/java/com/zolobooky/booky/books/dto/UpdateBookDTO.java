package com.zolobooky.booky.books.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateBookDTO {

	String name;

	String author;

	String description;

	Integer maxBorrow;

	String thumbnail;

}
