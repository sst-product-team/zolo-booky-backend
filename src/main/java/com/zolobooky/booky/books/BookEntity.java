package com.zolobooky.booky.books;

import com.zolobooky.booky.commons.CustomStatus.BOOK_STATUS;
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
@Table(name = "books",
		uniqueConstraints = @UniqueConstraint(columnNames = { "book_name", "book_desc", "book_thumbnail" }))
public class BookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer book_id;

	@Column(nullable = false)
	private String book_name;

	String book_desc;

	@Column(nullable = false)
	BOOK_STATUS book_status;

	@Column(nullable = false)
	Date book_next_available;

	@Column(nullable = false)
	private Double book_rating;

	byte[] book_thumbnail;

	Integer owner_id;

}
