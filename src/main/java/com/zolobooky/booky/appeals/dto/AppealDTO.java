package com.zolobooky.booky.appeals.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AppealDTO {
    Integer trans_id;
    Integer book_id;
    Integer borrower_id;

    String trans_status;

    Date initiation_date;
    Date expected_completion_date;
    Date status_change_date;
    Date completion_date;

    public AppealDTO(Integer trans_id, Integer book_id, Integer borrower_id) {
        setTrans_id(trans_id);
        setBook_id(book_id);
        setBorrower_id(borrower_id);

        setTrans_status("Pending");

        setInitiation_date(new Date());
        setStatus_change_date(new Date());
        setExpected_completion_date(new Date());
        setCompletion_date(new Date());
    }
}
