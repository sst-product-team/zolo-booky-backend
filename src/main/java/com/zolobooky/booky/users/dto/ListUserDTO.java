package com.zolobooky.booky.users.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class ListUserDTO {

	@NonNull
	Integer userId;

	@NotNull
	String userName;

	@NotNull
	String fcmToken;

}
