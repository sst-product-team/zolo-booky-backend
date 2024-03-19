package com.zolobooky.booky.appeals.dto;

import java.sql.Date;

import com.zolobooky.booky.commons.CustomStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortAppealDTO {

	Integer id;

	Integer bookid;

	String borrower;

	Date returnDate;

	CustomStatus.TransactionStatus status;

}
