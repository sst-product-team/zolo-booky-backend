package com.zolobooky.booky.users.dto;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class CreateUserDTO {

	@NotNull
	String fcmToken;

}
