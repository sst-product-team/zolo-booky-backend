package com.zolobooky.booky.appeals;

import com.zolobooky.booky.appeals.dto.AppealDTO;
import com.zolobooky.booky.books.BookExceptions.BookNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AppealService {

	private final AppealRepository appealRepository;

	private final ModelMapper modelMapper;

	public AppealService(AppealRepository appealRepository, ModelMapper modelMapper) {
		this.appealRepository = appealRepository;
		this.modelMapper = modelMapper;
	}

	public List<AppealEntity> getAllAppeals() {
		return appealRepository.findAll();
	}

	public AppealEntity getAppeal(Integer trans_id) {
		Optional<AppealEntity> appealEntity = appealRepository.findById(trans_id);

		if (appealEntity.isEmpty()){
			throw new BookNotFoundException("Appeal not found");
		}

		return appealEntity.get();

	}

	public AppealEntity createAppeal(AppealDTO appealDTO) {
		AppealEntity appealEntity = modelMapper.map(appealDTO, AppealEntity.class);
		return appealRepository.save(appealEntity);
	}

	public AppealEntity updateAppealStatus(Integer trans_id, AppealDTO appealDTO) {
		AppealEntity appealDataEntity = modelMapper.map(appealDTO, AppealEntity.class);
		Optional<AppealEntity> appealEntity = appealRepository.findById(trans_id);

		if (appealEntity.isEmpty()){
			throw new BookNotFoundException("appeal not found");
		}

		return appealRepository.save(appealEntity.get());
	}

}
