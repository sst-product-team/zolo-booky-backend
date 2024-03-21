package com.zolobooky.booky.notifications;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

@Configuration
public class FireInit {

	@Value("${app.fire.config}")
	private String fireConfig;

	@Bean
	FirebaseApp createFirebaseApp() throws IOException {
		FirebaseOptions options = FirebaseOptions.builder()
			.setCredentials(GoogleCredentials.fromStream(new ClassPathResource(fireConfig).getInputStream()))
			.build();

		if (FirebaseApp.getApps().isEmpty()) {
			return FirebaseApp.initializeApp(options);
		}
		else {
			return FirebaseApp.getInstance();
		}
	}

	@Bean
	public FirebaseMessaging firebaseMessaging() throws IOException {
		return FirebaseMessaging.getInstance(createFirebaseApp());
	}

}
