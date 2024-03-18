package com.zolobooky.booky.users.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {

	@NonNull
	String fcmToken;

}
