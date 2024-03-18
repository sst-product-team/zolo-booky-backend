package com.zolobooky.booky.books.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBookDTO {

	String name;

	String author;

	String description;

	Date availability;

	String thumbnail;

	String owner;

}
