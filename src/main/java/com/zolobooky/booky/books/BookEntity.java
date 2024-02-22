package com.zolobooky.booky.books;

import com.zolobooky.booky.commons.CustomStatus.BookStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "books")
@Table(name = "books", uniqueConstraints = @UniqueConstraint(columnNames = { "bookName", "bookDesc", "bookThumbnail" }))
public class BookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String name;

	String description;

	@Column(nullable = false)
	BookStatus status = BookStatus.AVAILABLE;

	@Column(nullable = false)
	Date availability;

	private Double rating;

	byte[] thumbnail;

	Integer owner;

}
