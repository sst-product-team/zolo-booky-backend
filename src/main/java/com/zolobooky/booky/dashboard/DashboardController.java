package com.zolobooky.booky.dashboard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("")
public class DashboardController {

	@GetMapping("")
	public LocalDateTime greeting() {
		return LocalDateTime.now();
	}

}