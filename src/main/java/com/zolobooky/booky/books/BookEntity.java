package com.zolobooky.booky.books;

import com.zolobooky.booky.books.dto.CreateBookDTO;
import com.zolobooky.booky.commons.CustomStatus.BookStatus;

import com.zolobooky.booky.users.UserEntity;
import jakarta.persistence.*;
import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity(name = "books")
@Table(name = "books")
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
	@ManyToOne
	@JoinColumn(name = "owner", nullable = false)
	UserEntity owner;

	public BookEntity() {
	}

	public BookEntity(CreateBookDTO createBookDTO) {
		this.name = createBookDTO.getName();
		this.author = createBookDTO.getAuthor();
		this.description = createBookDTO.getDescription();
		this.availability = createBookDTO.getAvailability();
		this.thumbnail = createBookDTO.getThumbnail();
		this.owner = null;
	}

}
