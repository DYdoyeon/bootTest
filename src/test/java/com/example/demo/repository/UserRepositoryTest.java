package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.*;
import com.example.demo.model.entity.User;

public class UserRepositoryTest extends BootjarTestApplicationTests {
	// DI
	@Autowired
	private UserRepository userRepository;

	// @Test
	public void create() {
		// String sql = insert into user (%s, %s, %d) value (account,email,age);
		User user = new User();
		user.setAccount("TestUser03");
		user.setEmail("testuser03@gmail.com");
		user.setPhoneNumber("010-2202-3333");
		user.setCreatedAt(LocalDateTime.now());
		user.setCreatedBy("Testuser03");

		User newUser = userRepository.save(user);
		System.out.println("newUser : " + newUser);
	}

	 //@Test
	public void read() {
//	public User read(@RequestParam Long id) {
		Optional<User> user = userRepository.findById(2L);
		user.ifPresent(selectUser -> {
			System.out.println("user : " + selectUser);
		});

		// return user.get();

	}

	// @Test
	public void update() {

		Optional<User> user = userRepository.findById(2L);
		user.ifPresent(selectUser -> {
			// selectUser.setId(3L);
			selectUser.setAccount("pppp");
			selectUser.setUpdateBy("update method()");
			selectUser.setUpdateAt(LocalDateTime.now());
			userRepository.save(selectUser);
		});
	}

//	@DeleteMapping("/api/usr")
	//@Test
	@Transactional
	public void delete() {
		Optional<User> user = userRepository.findById(5L);
		Assert.assertTrue(user.isPresent());
		user.ifPresent(selectUser -> {
			userRepository.delete(selectUser);

		});
		Optional<User> deleteUser = userRepository.findById(5L);
		Assert.assertFalse(deleteUser.isPresent());
		/*
		 * if (deleteUser.isPresent()) { System.out.println("데이터 존재 : " +
		 * deleteUser.get()); } else { System.out.println("데이터 삭제 데이터없음");
		 * 
		 * }
		 */
	}

}
