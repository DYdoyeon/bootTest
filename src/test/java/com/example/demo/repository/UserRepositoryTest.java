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
		String account = "Test01";
		String password = "Test01";

		String status = "REGISTERED";
		String email = "Test01@naver.com";
		String phoneNumber = "010-1111-1111";
		LocalDateTime registedAt = LocalDateTime.now();
		LocalDateTime createdAt = LocalDateTime.now();

		String createdBy = "AdminServer";

		User user = new User();

		user.setAccount(account);
		user.setPassword(password);
		user.setStatus(status);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setCreatedAt(createdAt);
		user.setCreatedBy(createdBy);
		user.setRegisteredAt(registedAt);

		User newUser = userRepository.save(user);
		Assert.assertNotNull(newUser);
		System.out.println("newUser : " + newUser);
	}

	@Test
	@Transactional
	public void read() {

		User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-1111");
		if (user != null) {
			user.getOrderGroupList().stream().forEach(orderGroup -> {
				System.out.println("수령인 : " + orderGroup.getRevName());
				System.out.println("총금액 : " + orderGroup.getTotalPrice());
				System.out.println("수령지 : " + orderGroup.getRevAddress());
				System.out.println("총수량 : " + orderGroup.getTotalQuantity());
			});
		//	Assert.assertNotNull(user);

		}
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
	// @Test
//	@Transactional
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
