package com.zolobooky.booky.books;

import com.zolobooky.booky.commons.CustomStatus.BookStatus;

import com.zolobooky.booky.users.UserEntity;
import jakarta.persistence.*;

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

	@Column
	Date availability;

	private Double rating;

	String thumbnail;

	@PrimaryKeyJoinColumn
	@OneToOne
	@JoinColumn(name = "owner", nullable = false)
	UserEntity owner;

}
