package com.zolobooky.booky.books;

import com.zolobooky.booky.books.dto.CreateBookDTO;
import com.zolobooky.booky.commons.CustomStatus.BookStatus;

import com.zolobooky.booky.users.UserEntity;
import jakarta.persistence.*;
import java.sql.Date;

import lombok.Data;

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
	Integer maxBorrow;

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
		this.maxBorrow = createBookDTO.getMaxBorrow();
		this.thumbnail = createBookDTO.getThumbnail();
		this.owner = null;
	}

}
