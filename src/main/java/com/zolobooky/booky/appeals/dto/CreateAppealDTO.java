package com.zolobooky.booky.appeals.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAppealDTO {

	Integer bookid;

	String borrower;

	Date returndate;

}
