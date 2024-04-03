package com.zolobooky.booky;

import com.zolobooky.booky.appeals.AppealEntity;
import com.zolobooky.booky.books.BookEntity;
import com.zolobooky.booky.commons.CustomStatus;
import com.zolobooky.booky.users.UserEntity;

import java.sql.Date;
import java.util.List;

public class AppealAPITestAssets {
    public UserEntity demoUser;
    public BookEntity demoBook;

    public final List<AppealEntity> appealEntityList;

    public AppealEntity appealEntity1;
    public AppealEntity appealEntity2;
    public AppealEntity appealEntity3;
    public AppealEntity appealEntity4;

    public AppealAPITestAssets() {
        demoUser = new UserEntity();
        demoUser.setId(42);
        demoUser.setName("Demo");
        demoUser.setFcmToken("Demo-FCM-TOKEN");

        demoBook = new BookEntity();
        demoBook.setId(900);
        demoBook.setName("Test Book 2");
        demoBook.setStatus(CustomStatus.BookStatus.AVAILABLE);
        demoBook.setThumbnail("TEST IMAGE URL");
        demoBook.setOwner(demoUser);

        appealEntity1 = new AppealEntity();
        appealEntity1.setTrans_id(1);
        appealEntity1.setTrans_status(CustomStatus.TransactionStatus.PENDING);
        appealEntity1.setBook_id(demoBook);
        appealEntity1.setBorrower_id(demoUser);
        appealEntity1.setInitiation_date(new Date(1712174174));
        appealEntity1.setCompletion_date(new Date(1712174174));
        appealEntity1.setStatus_change_date(new Date(1712174174));
        appealEntity1.setExpected_completion_date(new Date(1712174174));

        appealEntity2 = new AppealEntity();
        appealEntity2.setTrans_id(2);
        appealEntity2.setTrans_status(CustomStatus.TransactionStatus.PENDING);
        appealEntity2.setBook_id(demoBook);
        appealEntity2.setBorrower_id(demoUser);
        appealEntity2.setInitiation_date(new Date(1712174174));
        appealEntity2.setCompletion_date(new Date(1712174174));
        appealEntity2.setStatus_change_date(new Date(1712174174));
        appealEntity2.setExpected_completion_date(new Date(1712174174));

        appealEntity3 = new AppealEntity();
        appealEntity3.setTrans_id(3);
        appealEntity3.setTrans_status(CustomStatus.TransactionStatus.PENDING);
        appealEntity3.setBook_id(demoBook);
        appealEntity3.setBorrower_id(demoUser);
        appealEntity3.setInitiation_date(new Date(1712174174));
        appealEntity3.setCompletion_date(new Date(1712174174));
        appealEntity3.setStatus_change_date(new Date(1712174174));
        appealEntity3.setExpected_completion_date(new Date(1712174174));

        appealEntity4 = new AppealEntity();
        appealEntity4.setTrans_id(4);
        appealEntity4.setTrans_status(CustomStatus.TransactionStatus.PENDING);
        appealEntity4.setBook_id(demoBook);
        appealEntity4.setBorrower_id(demoUser);
        appealEntity4.setInitiation_date(new Date(1712174174));
        appealEntity4.setCompletion_date(new Date(1712174174));
        appealEntity4.setStatus_change_date(new Date(1712174174));
        appealEntity4.setExpected_completion_date(new Date(1712174174));

        appealEntityList = List.of(appealEntity1, appealEntity2, appealEntity3, appealEntity4);
    }

    public List<AppealEntity> getAllAppeals() {
        return this.appealEntityList;
    }

    public AppealEntity getAppeal() {
        return this.appealEntity3;
    }

    public AppealEntity postAppeal() {
        return this.appealEntity4;
    }
}
