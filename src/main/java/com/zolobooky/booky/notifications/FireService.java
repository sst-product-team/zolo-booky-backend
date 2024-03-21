package com.zolobooky.booky.notifications;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FireService {

	private final FirebaseMessaging fcm;

	public FireService(FirebaseMessaging firebaseMessaging) {
		this.fcm = firebaseMessaging;
	}

	@Async
	public void sendNotification(String token, String title, String Body) {

		Notification notification = Notification.builder().setTitle(title).setBody(Body).build();

		Message msg = Message.builder().setToken(token).setNotification(notification).build();

		ApiFuture<String> resp = fcm.sendAsync(msg);

		log.info("Notification Sent." + resp.toString());
	}

}
