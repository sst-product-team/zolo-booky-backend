package com.zolobooky.booky.appeals;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AppealEntity {

    public enum TransactionStatus {
        Pending,
        Rejected,
        Ongoing,
        Completed
    }

    private int trans_id;
    private int book_id;
    private int borrower_id;

    private TransactionStatus trans_status;

    private Date initiation_date;
    private Date expected_completion_dt;
    private Date status_change_dt;
    private Date completion_dt;

}
