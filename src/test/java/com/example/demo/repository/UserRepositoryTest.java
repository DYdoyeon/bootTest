package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.BootjarTestApplicationTests;
import com.example.demo.model.entity.Item;
import com.example.demo.model.entity.User;

public class UserRepositoryTest extends BootjarTestApplicationTests {
	// DI
	@Autowired
	private UserRepository userRepository;

//	@Test
	public void create() {
		String account = "Test03";
		String password = "Test03";

		String status = "REGISTERED";
		String email = "Test03@naver.com";
		String phoneNumber = "010-1111-3333";
		LocalDateTime registedAt = LocalDateTime.now();

		User user = new User();

		user.setAccount(account);
		user.setPassword(password);
		user.setStatus(status);
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		user.setRegisteredAt(registedAt);

		User u = User.builder().account(account).password(password).status(status).email(email).build();

		User newUser = userRepository.save(user);
		Assert.assertNotNull(newUser);
		System.out.println("newUser : " + newUser);
	}

	@Test
	@Transactional
	public void read() {

		User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-2222");

		if (user != null) {
			user.getOrderGroupList().stream().forEach(orderGroup -> {
				System.out.println("==========주문묶음=========");
				System.out.println("수령인 : " + orderGroup.getRevName());
				System.out.println("총금액 : " + orderGroup.getTotalPrice());
				System.out.println("수령지 : " + orderGroup.getRevAddress());
				System.out.println("총수량 : " + orderGroup.getTotalQuantity());
				System.out.println("==========주문상세=========");
				orderGroup.getOrderDetailList().forEach(orderDetail -> {
					System.out.println("주문 상품 : " + orderDetail.getItem().getName());
					System.out.println("고객센터 번호 : " + orderDetail.getItem().getPartner().getCallCenter());
					System.out.println("주문의 상태 : " + orderDetail.getStatus());
					System.out.println("도착예정일자 : " + orderDetail.getArrivalDate());

				});
			});
		}

		Assert.assertNotNull(user);

	}

	// @Test
	public void update() {

		Optional<User> user = userRepository.findById(2L);
		user.ifPresent(selectUser -> {
			// selectUser.setId(3L);
			selectUser.setAccount("pppp");
			selectUser.setUpdatedBy("update method()");
			selectUser.setUpdatedAt(LocalDateTime.now());
			userRepository.save(selectUser);
		});
	}

//	@DeleteMapping("/api/usr")
	// @Test
//	@Transactiona
	public void delete() {
		Optional<User> user = userRepository.findById(5L);
		Assert.assertTrue(user.isPresent());
		user.ifPresent(selectUser -> {
			userRepository.delete(selectUser);

		});
		Optional<User> deleteUser = userRepository.findById(5L);
		Assert.assertFalse(deleteUser.isPresent());

	}

}
