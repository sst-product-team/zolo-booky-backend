package com.zolobooky.booky.appeals.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import com.zolobooky.booky.books.dto.BookDTO;
import com.zolobooky.booky.commons.CustomStatus;

@Getter
@Setter
public class AppealDTO {

	Integer id;

	Integer bookid;

	BookDTO book;

	String borrower;

	Date returndate;

	CustomStatus.TransactionStatus status;

}
