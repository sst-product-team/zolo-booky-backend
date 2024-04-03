package com.zolobooky.booky.books;

import java.util.List;

import com.zolobooky.booky.users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zolobooky.booky.commons.CustomStatus.BookStatus;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {

	List<BookEntity> findByStatusOrderByName(BookStatus status);

	List<BookEntity> findByOwnerOrderByName(UserEntity owner);

	List<BookEntity> findAllByNameContainsIgnoreCaseAndAuthorContainsIgnoreCase(String name, String author);

}
