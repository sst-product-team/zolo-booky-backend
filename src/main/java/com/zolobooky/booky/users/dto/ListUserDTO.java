package com.zolobooky.booky.users.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ListUserDTO {

	@NonNull
	Integer userId;

	@NonNull
	String userName;

	@NonNull
	String fcmToken;

}
