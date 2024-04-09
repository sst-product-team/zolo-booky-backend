package com.zolobooky.booky.appeals;

import com.zolobooky.booky.appeals.AppealExceptions.AppealAlreadyExistsException;
import com.zolobooky.booky.appeals.AppealExceptions.AppealNotFoundException;
import com.zolobooky.booky.appeals.dto.CreateAppealDTO;
import com.zolobooky.booky.appeals.dto.UpdateAppealDTO;
import com.zolobooky.booky.books.BookEntity;
import com.zolobooky.booky.books.BookRepository;
import com.zolobooky.booky.books.BookExceptions.BadRequestException;
import com.zolobooky.booky.books.BookExceptions.BookNotFoundException;
import com.zolobooky.booky.books.BookService;
import com.zolobooky.booky.commons.CustomStatus;
import com.zolobooky.booky.notifications.FireService;
import com.zolobooky.booky.users.UserEntity;
import com.zolobooky.booky.users.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class AppealService {

	private final AppealRepository appealRepository;

	private final BookRepository bookRepository;

	private final BookService bookService;

	private final UserService userService;

	@Autowired
	private final FireService fireService;

	public AppealService(AppealRepository appealRepository, BookService bookService, UserService userService,
			FireService fireService, BookRepository bookRepository) {
		this.appealRepository = appealRepository;
		this.bookService = bookService;
		this.userService = userService;
		this.fireService = fireService;
		this.bookRepository = bookRepository;
	}

	public List<AppealEntity> getAllAppeals(Integer book, Integer user) {
		if (book != -1 && user != -1) {
			BookEntity bookEntity = bookService.getBookById(book);
			UserEntity userEntity = userService.getUser(user);
			return appealRepository.findByBookIdAndBorrowerId(bookEntity, userEntity);
		}
		else if (book != -1) {
			BookEntity bookEntity = bookService.getBookById(book);
			return appealRepository.findByBookId(bookEntity);
		}
		else if (user != -1) {
			UserEntity userEntity = userService.getUser(user);
			return appealRepository.findByBorrowerId(userEntity);
		}
		List<AppealEntity> appeals = appealRepository.findAll();

		log.info(String.format("%s appeals fetched from the database.", appeals.size()));

		return appeals;

	}

	public AppealEntity getAppeal(Integer trans_id) {
		Optional<AppealEntity> appealEntity = appealRepository.findById(trans_id);

		if (appealEntity.isEmpty()) {
			throw new AppealNotFoundException("Appeal with id " + trans_id + " does not exist");
		}

		AppealEntity appeal = appealEntity.get();

		log.info(String.format("appeal with id: %s fetched from the database.", appeal.getTrans_id()));

		return appeal;
	}

	public AppealEntity createAppeal(CreateAppealDTO createAppealDTO) {

		if (createAppealDTO.getBookId() == null || createAppealDTO.getBorrowerId() == null
				|| createAppealDTO.getExpected_completion_date() == null) {
			throw new BadRequestException("BAD REQUEST: Missing required body");
		}

		BookEntity book = bookService.getBookById(createAppealDTO.getBookId());
		UserEntity user = userService.getUser(createAppealDTO.getBorrowerId());

		List<AppealEntity> appeals = this.appealRepository.findAll();

		for (var appeal : appeals) {
			CustomStatus.TransactionStatus ongoing = CustomStatus.TransactionStatus.ONGOING;
			CustomStatus.TransactionStatus pending = CustomStatus.TransactionStatus.PENDING;
			if (appeal.getBorrowerId().getId().equals(createAppealDTO.getBorrowerId())
					&& appeal.getBookId().getId().equals(book.getId()) && appeal.getTrans_status() == pending) {
				log.info(String.format("pending appeal by the same user : %s already exists for the book.",
						createAppealDTO.getBorrowerId()));
				throw new AppealAlreadyExistsException("pending appeal by the same user already exists for the book.");
			}

			if (Objects.equals(appeal.getBookId().getId(), book.getId()) && appeal.getTrans_status() == ongoing) {
				log.info(
						String.format("Ongoing appeal for this book with id: %s already exists", appeal.getTrans_id()));
				throw new AppealAlreadyExistsException("Ongoing appeal for this book exists");
			}

		}

		Date intiDate = new Date();
		AppealEntity appealEntity = new AppealEntity();
		appealEntity.setBookId(book);
		appealEntity.setBorrowerId(user);
		appealEntity.setInitiation_date(intiDate);
		appealEntity.setExpected_completion_date(createAppealDTO.getExpected_completion_date());
		book.setRequestCount(book.getRequestCount() + 1);
		this.bookRepository.save(book);

		this.fireService.sendNotification(book.getOwner().getFcmToken(),
				String.format("Book request for %s", book.getName()),
				String.format("%s wants to borrow your book %s", user.getName(), book.getName()));

		log.info(String.format("appeal with id: %s created successfully.", appealEntity.getTrans_id()));
		return appealRepository.save(appealEntity);
	}

	public AppealEntity updateAppealStatus(Integer trans_id, UpdateAppealDTO appealDTO) {
		Optional<AppealEntity> appealEntity = appealRepository.findById(trans_id);
		if (appealEntity.isEmpty()) {
			log.warn("appeal not found");
			throw new BookNotFoundException("appeal not found");
		}

		if (appealDTO.getTrans_status() == null) {
			log.warn("appeal not found");
			throw new BadRequestException("Missing required body {status}");
		}
		Date then = new Date();
		AppealEntity appeal = appealEntity.get();
		BookEntity book = appeal.getBookId();

		if (appealDTO.getTrans_status() == CustomStatus.TransactionStatus.ONGOING) {
			book.setRequestCount(book.getRequestCount() - 1);
			this.bookRepository.save(book);

			bookService.updateStatus(book.getId(), CustomStatus.BookStatus.UNAVAILABLE);
			log.info(String.format("request accepted for %s", book.getName()));
			this.fireService.sendNotification(appeal.getBorrowerId().getFcmToken(),
					String.format("Request accepted for %s", book.getName()),
					String.format("Please collect %s from %s and enjoy your read.", book.getName(),
							book.getOwner().getName()));

		}

		if (appealDTO.getTrans_status() == CustomStatus.TransactionStatus.COMPLETED
				|| appealDTO.getTrans_status() == CustomStatus.TransactionStatus.REJECTED) {
			bookService.updateStatus(book.getId(), CustomStatus.BookStatus.AVAILABLE);

			if (appealDTO.getTrans_status() == CustomStatus.TransactionStatus.COMPLETED) {

				log.info(String.format("%s book return completed.", book.getName()));
				this.fireService.sendNotification(appeal.getBorrowerId().getFcmToken(),
						String.format("%s book recieved.", book.getName()),
						"Thanks for using Zolo-booky.Hope you had a great experience.");
				this.fireService.sendNotification(book.getOwner().getFcmToken(),
						String.format("%s book return completed.", book.getName()),
						"Thanks for using Zolo-booky.Hope you had a great experience.");
			}
			else {
				book.setRequestCount(book.getRequestCount() - 1);
				this.bookRepository.save(book);

				log.info(String.format("request for %s book has been rejected.", book.getName()));
				this.fireService.sendNotification(appeal.getBorrowerId().getFcmToken(),
						String.format("Your request for %s book has been rejected.", book.getName()),
						"Sorry for the inconvenience.Hope to see you next time.");
			}
		}

		appeal.setTrans_status(appealDTO.getTrans_status());
		appeal.setStatus_change_date(then);
		log.info(String.format("status of appeal with id: %s updated to %s successfully.", appeal.getTrans_id(),
				appeal.getTrans_status()));
		return appealRepository.save(appeal);
	}



}
