package com.zolobooky.booky.notifications;

import com.zolobooky.booky.notifications.dto.CreateFireDTO;
import com.zolobooky.booky.notifications.dto.SendFireDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v0/notification")
@EnableAsync(proxyTargetClass = true)
public class FireController {

	private final FireService fireService;

	public FireController(FireService fireService) {
		this.fireService = fireService;
	}

	@PostMapping("")
	public ResponseEntity<SendFireDTO> sendNotification(@RequestBody CreateFireDTO createFire) {

		this.fireService.sendNotification(createFire.getToken(), createFire.getTitle(), createFire.getBody());

		SendFireDTO fire = new SendFireDTO();
		fire.setMessage("notification sending initiated.");

		return ResponseEntity.ok(fire);
	}

}
