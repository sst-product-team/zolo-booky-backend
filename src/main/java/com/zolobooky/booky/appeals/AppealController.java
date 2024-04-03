package com.zolobooky.booky.appeals;

import com.zolobooky.booky.appeals.dto.AppealDTO;

import com.zolobooky.booky.appeals.dto.CreateAppealDTO;
import com.zolobooky.booky.appeals.dto.UpdateAppealDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AppealController {

	@Autowired
	private AppealService appealService;

	private final ModelMapper modelMapper;

	public AppealController(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@GetMapping("/v0/appeals")
	public List<AppealDTO> getAllAppeals(@RequestParam(value = "book", defaultValue = "-1") Integer book,
			@RequestParam(value = "borrower", defaultValue = "-1") Integer user) {
		List<AppealEntity> appealsList = appealService.getAllAppeals(book, user);
		List<AppealDTO> appealDTOList = new ArrayList<>();

		for (AppealEntity appealEntity : appealsList) {
			appealDTOList.add(modelMapper.map(appealEntity, AppealDTO.class));
		}

		return appealDTOList;
	}

	@GetMapping("/v0/appeals/{trans_id}")
	public AppealDTO getAppeal(@PathVariable("trans_id") Integer trans_id) {
		return modelMapper.map(appealService.getAppeal(trans_id), AppealDTO.class);
	}

	@PostMapping("/v0/appeals")
	public ResponseEntity<AppealDTO> createAppeal(@RequestBody CreateAppealDTO createAppealDTO) {
		AppealEntity appealRegistered = appealService.createAppeal(createAppealDTO);
		return ResponseEntity.ok(this.modelMapper.map(appealRegistered, AppealDTO.class));
	}

	@PatchMapping("/v0/appeals/{trans_id}")
	public ResponseEntity<AppealDTO> updateAppealStatus(@PathVariable Integer trans_id,
			@RequestBody UpdateAppealDTO appealDTO) {
		AppealEntity updatedAppeal = appealService.updateAppealStatus(trans_id, appealDTO);
		return ResponseEntity.ok(this.modelMapper.map(updatedAppeal, AppealDTO.class));
	}

}
