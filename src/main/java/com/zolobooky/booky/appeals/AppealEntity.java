package com.zolobooky.booky.appeals;

import java.util.Date;

import com.zolobooky.booky.books.BookEntity;
import com.zolobooky.booky.commons.CustomStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "appeals")
public class AppealEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "book_id", nullable = false)
	private int bookid;

	@OneToOne
	@JoinColumn(name = "book_id", insertable = false, updatable = false)
	private BookEntity book;

	@Column(nullable = false)
	Date returndate;

	CustomStatus.TransactionStatus status = CustomStatus.TransactionStatus.PENDING;

}
