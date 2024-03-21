package com.zolobooky.booky.appeals;

import com.zolobooky.booky.books.BookEntity;
import com.zolobooky.booky.commons.CustomStatus;
import com.zolobooky.booky.users.UserEntity;
import jakarta.persistence.*;
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

	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private BookEntity book_id;

	@ManyToOne
	@JoinColumn(name = "borrower_id", nullable = false)
	private UserEntity borrower_id;

	private CustomStatus.TransactionStatus trans_status = CustomStatus.TransactionStatus.PENDING;

	private Date initiation_date;

	private Date expected_completion_date;

	private Date status_change_date;

	private Date completion_date;

	public AppealEntity() {

	}

	@Override
	public String toString() {
		return "AppealEntity{" + "trans_id=" + trans_id + ", book_id=" + book_id + ", borrower_id=" + borrower_id
				+ ", trans_status=" + trans_status + ", initiation_date=" + initiation_date
				+ ", expected_completion_dt=" + expected_completion_date + ", status_change_dt=" + status_change_date
				+ ", completion_dt=" + completion_date + '}';
	}

}
