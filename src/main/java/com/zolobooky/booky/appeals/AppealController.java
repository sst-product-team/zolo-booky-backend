package com.zolobooky.booky.appeals;

import com.zolobooky.booky.appeals.dto.AppealDTO;
import com.zolobooky.booky.appeals.dto.CreateAppealDTO;
import com.zolobooky.booky.appeals.dto.ShortAppealDTO;
import com.zolobooky.booky.appeals.dto.StatusAppealDTO;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AppealController {

	private final AppealService appealService;

	private final ModelMapper modelMapper;

	public AppealController(ModelMapper modelMapper, AppealService appealService) {
		this.modelMapper = modelMapper;
		this.appealService = appealService;
	}

	@GetMapping("/v0/appeals")
	public ResponseEntity<List<AppealDTO>> getAllAppeals() {
		List<AppealEntity> appealsList = appealService.getAllAppeals();
		List<AppealDTO> appealDTOList = new ArrayList<>();

		for (AppealEntity appealEntity : appealsList) {
			appealDTOList.add(modelMapper.map(appealEntity, AppealDTO.class));
		}

		return ResponseEntity.ok(appealDTOList);
	}

	@GetMapping("/v0/appeals/{id}")
	public ResponseEntity<AppealDTO> getAppeal(@PathVariable("id") Integer id) {

		AppealEntity appealEntity = this.appealService.getAppealById(id);
		return ResponseEntity.ok(modelMapper.map(appealEntity, AppealDTO.class));
	}

	@PostMapping("/v0/appeals")
	public ResponseEntity<ShortAppealDTO> createAppeal(@RequestBody CreateAppealDTO createAppealDTO) {

		AppealEntity appealEntity = this.appealService.createAppeal(createAppealDTO);
		return ResponseEntity.ok(modelMapper.map(appealEntity, ShortAppealDTO.class));
	}

	@PatchMapping("/v0/appeals/{id}")
	public ResponseEntity<ShortAppealDTO> updateAppealStatus(@PathVariable Integer id,
			@RequestBody StatusAppealDTO statusAppealDTO) {

		AppealEntity appealEntity = this.appealService.updateAppealStatusById(id, statusAppealDTO);
		return ResponseEntity.ok(modelMapper.map(appealEntity, ShortAppealDTO.class));
	}

}
