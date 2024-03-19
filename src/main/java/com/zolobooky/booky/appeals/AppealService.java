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
import com.zolobooky.booky.users.UserEntity;
import com.zolobooky.booky.users.UserService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AppealService {

	private final AppealRepository appealRepository;

	private final BookService bookService;

	private final UserService userService;

	public AppealService(AppealRepository appealRepository, BookService bookService, UserService userService) {
		this.appealRepository = appealRepository;
		this.bookService = bookService;
		this.userService = userService;
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
			CustomStatus.TransactionStatus ongoing = CustomStatus.TransactionStatus.Ongoing;
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
		if (appealDTO.getTrans_status() == CustomStatus.TransactionStatus.Ongoing) {
			bookService.updateStatus(book.getId(), CustomStatus.BookStatus.UNAVAILABLE);
		}
		if (appealDTO.getTrans_status() == CustomStatus.TransactionStatus.Completed
				|| appealDTO.getTrans_status() == CustomStatus.TransactionStatus.Rejected) {
			bookService.updateStatus(book.getId(), CustomStatus.BookStatus.AVAILABLE);
		}
		appeal.setTrans_status(appealDTO.getTrans_status());
		appeal.setStatus_change_date(then);
		return appealRepository.save(appeal);
	}

}
