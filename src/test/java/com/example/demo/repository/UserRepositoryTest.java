package com.example.demo.repository;

import java.time.LocalDateTime;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.*;
import com.example.demo.model.entity.User;

public class UserRepositoryTest extends BootjarTestApplicationTests {
	// DI
	@Autowired
	private UserRepository userRepository;

	@Test
	public void create() {
		// String sql = insert into user (%s, %s, %d) value (account,email,age);
		User user = new User();
		user.setAccount("TestUser03");
		user.setEmail("testuser03@gmail.com");
		user.setPhoneNumber("010-2202-3333");
		user.setCreatedAt(LocalDateTime.now());
		user.setCreatedBy("Testuser03");
		
		User newUser= userRepository.save(user);
		System.out.println("newUser : "+newUser);
	}

	public void read() {
	}

	public void update() {
	}

	public void delete() {
	}

}
