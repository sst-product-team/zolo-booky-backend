package com.zolobooky.booky.notifications;

import org.springframework.scheduling.annotation.Async;

public class NotificationService {

	@Async
	public void bookCreatedNotification() throws InterruptedException {
		System.out.println("STARTING ASYNC");
		Thread.sleep(10000L);
		System.out.println("ASYNC COMPLETED");
	}

}
