package com.zolobooky.booky.appeals;

import com.zolobooky.booky.appeals.dto.AppealDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppealController {

    @Autowired
    private AppealService appealService;

    @GetMapping("/api/v0/appeals")
    public List<AppealDTO> getAllAppeals() {
        return appealService.getAllAppeals();
    }

    @GetMapping("/api/v0/appeals/{trans_id}")
    public AppealDTO getAppeal(@PathVariable("trans_id") Integer trans_id) {
        return appealService.getAppeal(trans_id);
    }

    @PostMapping("/api/v0/appeals")
    public ResponseEntity<AppealDTO> createNewRequest(@RequestBody AppealDTO appealDTO) {
        AppealDTO appealRegistered = appealService.addAppeal(appealDTO);
        return ResponseEntity.ok(appealDTO);
    }

}
