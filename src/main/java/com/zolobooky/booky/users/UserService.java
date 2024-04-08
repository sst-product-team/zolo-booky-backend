package com.zolobooky.booky.users;

import com.zolobooky.booky.books.BookExceptions.BadRequestException;
import com.zolobooky.booky.users.dto.CreateUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class UserService {

	private final UserRepository userRepository;

	private final ModelMapper modelMapper;

	public UserService(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	public UserEntity getUser(Integer id) {
		if (id == null) {
			throw new BadRequestException("Found unexpected null value(s): User ID");
		}
		return this.userRepository.getReferenceById(id);
	}

	public UserEntity getUser(Integer userId, String fcmToken) {

		if (userId == -1) {
			UserEntity possibleUser = this.userRepository.findByFcmToken(fcmToken);
			if (possibleUser == null) {
				CreateUserDTO createUserDTO = new CreateUserDTO();
				createUserDTO.setFcmToken(fcmToken);
				return createUser(createUserDTO);
			}
			else {
				return possibleUser;
			}
		}

		UserEntity user = this.getUser(userId);
		if (Objects.equals(user.getFcmToken(), fcmToken))
			return user;
		user.setFcmToken(fcmToken);
		return this.userRepository.save(user);
	}

	public UserEntity createUser(CreateUserDTO createUserDTO) {
		int userCount = (int) this.userRepository.count();
		UserEntity user = modelMapper.map(createUserDTO, UserEntity.class);
		if (user.getFcmToken() == null) {
			throw new BadRequestException("Found unexpected null value(s): FCM Token");
		}

		if (this.userRepository.findByFcmToken(createUserDTO.getFcmToken()) == null) {
			user.setName(String.format("Zolo Booky User %d", userCount + 1));
			UserEntity createdUser = this.userRepository.save(user);
			int userId = createdUser.getId();
			log.info(String.format("User created with user ID %d", userId));
			return createdUser;
		}

		int userId = this.userRepository.findByFcmToken(createUserDTO.getFcmToken()).getId();
		log.info(String.format("User fetched with user ID %d", userId));

		return this.userRepository.findByFcmToken(createUserDTO.getFcmToken());

	}

}
