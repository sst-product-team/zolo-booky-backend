package com.zolobooky.booky.appeals.dto;

import com.zolobooky.booky.books.BookEntity;
import com.zolobooky.booky.users.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppealDTO {

	Integer trans_id;

	BookEntity bookId;

	UserEntity borrowerId;

	String trans_status;

	String initiation_date;

	String expected_completion_date;

	String status_change_date;

	String completion_date;

}
