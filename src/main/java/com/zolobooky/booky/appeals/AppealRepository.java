package com.zolobooky.booky.appeals;

import com.zolobooky.booky.books.BookEntity;
import com.zolobooky.booky.users.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppealRepository extends JpaRepository<AppealEntity, Integer> {

	List<AppealEntity> findByBookId(BookEntity bookID);

	List<AppealEntity> findByBorrowerId(UserEntity borrowerId);

	List<AppealEntity> findByBookIdAndBorrowerId(BookEntity bookId, UserEntity borrowerId);

}
