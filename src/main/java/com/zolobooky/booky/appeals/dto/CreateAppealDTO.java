package com.zolobooky.booky.appeals.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreateAppealDTO {

	Integer bookId;

	Integer borrowerId;

	Date expected_completion_date;

	public CreateAppealDTO(Integer book_id, Integer borrower_id, Date expected_completion_date) {
		this.bookId = book_id;
		this.borrowerId = borrower_id;
		this.expected_completion_date = expected_completion_date;
	}

	@Override
	public String toString() {
		return "CreateAppealDTO{" + "book_id=" + bookId + ", borrower_id=" + borrowerId + ", expected_completion_date='"
				+ expected_completion_date + '\'' + '}';
	}

}
