package com.zolobooky.booky.dashboard;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("")
public class DashboardController {

	@GetMapping("")
	public List<String> greeting() {
		return List.of("API is up and running");
	}

}