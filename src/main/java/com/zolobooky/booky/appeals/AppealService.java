package com.zolobooky.booky.appeals;

import com.zolobooky.booky.appeals.appealsExceptions.AppealsNotFoundExceptions;
import com.zolobooky.booky.appeals.dto.CreateAppealDTO;
import com.zolobooky.booky.appeals.dto.StatusAppealDTO;
import com.zolobooky.booky.books.BookEntity;
import com.zolobooky.booky.books.BookRepository;
import com.zolobooky.booky.books.BookExceptions.BadRequestException;
import com.zolobooky.booky.commons.CustomStatus;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppealService {

	private final AppealRepository appealRepository;

	private final BookRepository bookRepository;

	private final ModelMapper modelMapper;

	public AppealService(AppealRepository appealRepository, ModelMapper modelMapper, BookRepository bookRepository) {
		this.appealRepository = appealRepository;
		this.modelMapper = modelMapper;
		this.bookRepository = bookRepository;
	}

	public List<AppealEntity> getAllAppeals() {
		return appealRepository.findAll();
	}

	public AppealEntity getAppealById(Integer id) {
		Optional<AppealEntity> appealEntity = appealRepository.findById(id);

		if (appealEntity.isEmpty()) {
			throw new AppealsNotFoundExceptions("no transaction found with id: " + id);
		}

		return appealEntity.get();

	}

	public AppealEntity createAppeal(CreateAppealDTO createAppealDTO) {

		if (createAppealDTO.getReturndate() == null || createAppealDTO.getBookid() == null) {
			throw new BadRequestException("Missing field:Requires {bookid: Integer, returndate: Date}");
		}
		AppealEntity appealEntity = modelMapper.map(createAppealDTO, AppealEntity.class);
		return this.appealRepository.save(appealEntity);
	}

	public AppealEntity updateAppealStatusById(Integer id, StatusAppealDTO statusAppealDTO) {

		Optional<AppealEntity> entity = this.appealRepository.findById(id);

		if (entity.isEmpty()) {
			throw new AppealsNotFoundExceptions("no transaction found with id: " + id);
		}

		AppealEntity appealEntity = entity.get();

		if (statusAppealDTO.getStatus().equals(CustomStatus.TransactionStatus.ONGOING)) {
			Integer bookid = appealEntity.getBookid();
			BookEntity bookEntity = this.bookRepository.findById(bookid).get();
			bookEntity.setStatus(CustomStatus.BookStatus.UNAVAILABLE);
			this.bookRepository.save(bookEntity);
		}

		if (statusAppealDTO.getStatus().equals(CustomStatus.TransactionStatus.COMPLETED)) {
			Integer bookid = appealEntity.getBookid();
			BookEntity bookEntity = this.bookRepository.findById(bookid).get();
			bookEntity.setStatus(CustomStatus.BookStatus.AVAILABLE);
			this.bookRepository.save(bookEntity);
		}

		appealEntity.setStatus(statusAppealDTO.getStatus());

		return this.appealRepository.save(appealEntity);
	}

}
