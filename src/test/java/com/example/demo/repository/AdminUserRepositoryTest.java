package com.example.demo.repository;

import java.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.BootjarTestApplicationTests;
import com.example.demo.model.entity.AdminUser;

public class AdminUserRepositoryTest extends BootjarTestApplicationTests {

	@Autowired
	private AdminUserRepository adminUserRepository;

	@Test
	public void create() {
		AdminUser adminUser = new AdminUser();
		adminUser.setAccount("AdminUser01");
		adminUser.setPassword("AdminUser01");
		//adminUser.setStatus("REGISTERED");
		adminUser.setRole("PARTNER");

		// adminUser.setCreatedAt(LocalDateTime.now());
		// adminUser.setCreatedBy("AdminServer");

		AdminUser newAdminUser = adminUserRepository.save(adminUser);
		Assert.assertNotNull(newAdminUser);

		newAdminUser.setAccount("CHANGE");
		adminUserRepository.save(newAdminUser);
	}
}