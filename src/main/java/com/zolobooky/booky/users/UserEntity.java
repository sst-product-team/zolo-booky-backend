package com.zolobooky.booky.users;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "users")
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@Column(nullable = false)
	private String userName;

	@Column(nullable = false)
	private String fcmToken;

}
