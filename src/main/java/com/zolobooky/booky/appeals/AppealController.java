package com.zolobooky.booky.appeals;

import com.zolobooky.booky.appeals.dto.AppealDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v0/appeals")
public class AppealController {

    @Autowired
    private AppealService appealService;

    private final ModelMapper modelMapper;

    public AppealController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping("")
    public List<AppealDTO> getAllAppeals() {
        List<AppealEntity> appealsList = appealService.getAllAppeals();
        List<AppealDTO> appealDTOList = new ArrayList<>();

        for (AppealEntity appealEntity: appealsList) {
            appealDTOList.add(modelMapper.map(appealEntity, AppealDTO.class));
        }

        return appealDTOList;
    }

    @GetMapping("/{trans_id}")
    public AppealDTO getAppeal(@PathVariable("trans_id") Integer trans_id) {
        Optional<AppealEntity> appealEntity = appealService.getAppeal(trans_id);
        return this.modelMapper.map(appealEntity, AppealDTO.class);
    }

    @PostMapping("")
    public ResponseEntity<AppealDTO> createAppeal(@RequestBody AppealDTO appealDTO) {
        AppealEntity appealRegistered = appealService.createAppeal(appealDTO);
        return ResponseEntity.ok(this.modelMapper.map(appealRegistered, AppealDTO.class));
    }

    @PatchMapping("/{trans_id}")
    public ResponseEntity<AppealDTO> updateAppealStatus(@PathVariable Integer trans_id, @RequestBody AppealDTO appealDTO) {
        Optional<AppealEntity> updatedAppeal = appealService.updateAppealStatus(trans_id, appealDTO);
        return ResponseEntity.ok(this.modelMapper.map(updatedAppeal, AppealDTO.class));
    }

}
