package com.zolobooky.booky.appeals;

import com.zolobooky.booky.appeals.AppealExceptions.AppealAlreadyExistsException;
import com.zolobooky.booky.appeals.AppealExceptions.AppealNotFoundException;
import com.zolobooky.booky.appeals.dto.CreateAppealDTO;
import com.zolobooky.booky.appeals.dto.UpdateAppealDTO;
import com.zolobooky.booky.books.BookEntity;
import com.zolobooky.booky.books.BookExceptions.BadRequestException;
import com.zolobooky.booky.books.BookExceptions.BookNotFoundException;
import com.zolobooky.booky.books.BookService;
import com.zolobooky.booky.commons.CustomStatus;
import com.zolobooky.booky.notifications.FireService;
import com.zolobooky.booky.users.UserEntity;
import com.zolobooky.booky.users.UserService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AppealService {

	private final AppealRepository appealRepository;

	private final BookService bookService;

	private final UserService userService;

	private final FireService fireService;

	public AppealService(AppealRepository appealRepository, BookService bookService, UserService userService,
			FireService fireService) {
		this.appealRepository = appealRepository;
		this.bookService = bookService;
		this.userService = userService;
		this.fireService = fireService;
	}

	public List<AppealEntity> getAllAppeals() {
		return appealRepository.findAll();
	}

	public AppealEntity getAppeal(Integer trans_id) {
		Optional<AppealEntity> appealEntity = appealRepository.findById(trans_id);

		if (appealEntity.isEmpty()) {
			throw new AppealNotFoundException("Appeal with id " + trans_id + " does not exist");
		}

		return appealEntity.get();

	}

	public AppealEntity createAppeal(CreateAppealDTO createAppealDTO) {

		if (createAppealDTO.getBook_id() == null || createAppealDTO.getBorrower_id() == null
				|| createAppealDTO.getExpected_completion_date() == null) {
			throw new BadRequestException("BAD REQUEST: Missing required body");
		}

		BookEntity book = bookService.getBookById(createAppealDTO.getBook_id());
		UserEntity user = userService.getUser(createAppealDTO.getBorrower_id());

		List<AppealEntity> appeals = this.appealRepository.findAll();
		for (var appeal : appeals) {
			CustomStatus.TransactionStatus ongoing = CustomStatus.TransactionStatus.ONGOING;
			if (Objects.equals(appeal.getBook_id().getId(), book.getId()) && appeal.getTrans_status() == ongoing) {
				throw new AppealAlreadyExistsException("Ongoing appeal for this book already exists");
			}
		}

		Date intiDate = new Date();
		AppealEntity appealEntity = new AppealEntity();
		appealEntity.setBook_id(book);
		appealEntity.setBorrower_id(user);
		appealEntity.setInitiation_date(intiDate);
		appealEntity.setExpected_completion_date(createAppealDTO.getExpected_completion_date());

		this.fireService.sendNotification(book.getOwner().getFcmToken(),
				String.format("Book request for %s", book.getName()),
				String.format("%s wants to borrow your book %s", user.getName(), book.getName()));
		return appealRepository.save(appealEntity);
	}

	public AppealEntity updateAppealStatus(Integer trans_id, UpdateAppealDTO appealDTO) {
		Optional<AppealEntity> appealEntity = appealRepository.findById(trans_id);
		if (appealEntity.isEmpty()) {
			throw new BookNotFoundException("appeal not found");
		}

		if (appealDTO.getTrans_status() == null) {
			throw new BadRequestException("Missing required body {status}");
		}
		Date then = new Date();
		AppealEntity appeal = appealEntity.get();
		BookEntity book = appeal.getBook_id();
		if (appealDTO.getTrans_status() == CustomStatus.TransactionStatus.ONGOING) {
			bookService.updateStatus(book.getId(), CustomStatus.BookStatus.UNAVAILABLE);

			this.fireService.sendNotification(appeal.getBorrower_id().getFcmToken(),
					String.format("Request accepted for %s", book.getName()),
					String.format("Please collect %s from %s and enjoy your read.", book.getName(),
							book.getOwner().getName()));

		}
		if (appealDTO.getTrans_status() == CustomStatus.TransactionStatus.COMPLETED
				|| appealDTO.getTrans_status() == CustomStatus.TransactionStatus.REJECTED) {
			bookService.updateStatus(book.getId(), CustomStatus.BookStatus.AVAILABLE);

			if (appealDTO.getTrans_status() == CustomStatus.TransactionStatus.COMPLETED) {
				this.fireService.sendNotification(appeal.getBorrower_id().getFcmToken(),
						String.format("%s book recieved.", book.getName()),
						"Thanks for using Zolo-booky.Hope you had a great experience.");
				this.fireService.sendNotification(book.getOwner().getFcmToken(),
						String.format("%s book return completed.", book.getName()),
						"Thanks for using Zolo-booky.Hope you had a great experience.");
			}
			else {
				this.fireService.sendNotification(appeal.getBorrower_id().getFcmToken(),
						String.format("Your request for %s book has been rejected.", book.getName()),
						"Sorry for the inconvenience.Hope to see you next time.");
			}
		}
		appeal.setTrans_status(appealDTO.getTrans_status());
		appeal.setStatus_change_date(then);
		return appealRepository.save(appeal);
	}

}
