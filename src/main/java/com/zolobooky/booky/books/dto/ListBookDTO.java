package com.zolobooky.booky.books.dto;

import com.zolobooky.booky.commons.CustomStatus.BookStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListBookDTO {

	Integer id;

	String name;

	String author;

	BookStatus status;

	String thumbnail;

	String owner;

}
