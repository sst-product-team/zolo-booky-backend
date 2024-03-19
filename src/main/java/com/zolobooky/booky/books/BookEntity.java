package com.zolobooky.booky.books;

import com.zolobooky.booky.commons.CustomStatus.BookStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "books")
@Table(name = "books", uniqueConstraints = @UniqueConstraint(columnNames = { "name", "description", "thumbnail" }))
public class BookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String author;

	String description;

	@Column(nullable = false)
	BookStatus status = BookStatus.AVAILABLE;

	@Column(nullable = true)
	Date availability;

	private Double rating;

	String thumbnail;

	@Column(nullable = false)
	String owner;

}
