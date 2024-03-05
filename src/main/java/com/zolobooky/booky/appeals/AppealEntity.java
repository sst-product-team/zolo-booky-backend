package com.zolobooky.booky.appeals;

import com.zolobooky.booky.commons.CustomStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "appeals")
public class AppealEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int trans_id;

	private int book_id;

	private int borrower_id;

	private CustomStatus.TransactionStatus trans_status = CustomStatus.TransactionStatus.Pending;

	private Date initiation_date;

	private Date expected_completion_dt;

	private Date status_change_dt;

	private Date completion_dt;

}
