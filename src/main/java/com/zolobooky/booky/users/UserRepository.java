
package com.zolobooky.booky.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	List<UserEntity> findByIdOrderByName = null;

	UserEntity findByFcmToken(String fcmToken);

}