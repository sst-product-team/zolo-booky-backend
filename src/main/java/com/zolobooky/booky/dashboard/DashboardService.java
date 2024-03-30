package com.zolobooky.booky.dashboard;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class DashboardService {

	public LocalDateTime greeting() {
		return LocalDateTime.now();
	}

}
