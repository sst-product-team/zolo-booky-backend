package com.zolobooky.booky.users;

import com.zolobooky.booky.books.BookExceptions.BadRequestException;
import com.zolobooky.booky.users.dto.CreateUserDTO;
import com.zolobooky.booky.users.dto.ListUserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v0/users")
public class UserController {

	private final UserService userService;

	private final ModelMapper modelMapper;

	public UserController(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ListUserDTO> getUserById(@PathVariable Integer id) {
		if (id == null) {
			throw new BadRequestException("Incomplete request");
		}
		UserEntity user = this.userService.getUser(id);
		var response = this.modelMapper.map(user, ListUserDTO.class);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/token/{fcmToken}")
	public ResponseEntity<ListUserDTO> getUserByFcm(@PathVariable String fcmToken) {
		if (fcmToken == null) {
			throw new BadRequestException("Incomplete request");
		}
		UserEntity user = this.userService.getUser(fcmToken);
		var response = this.modelMapper.map(user, ListUserDTO.class);
		return ResponseEntity.ok(response);
	}

	@PostMapping("")
	public ResponseEntity<ListUserDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
		var user = this.userService.createUser(createUserDTO);
		var response = this.modelMapper.map(user, ListUserDTO.class);
		return ResponseEntity.ok(response);
	}

}
