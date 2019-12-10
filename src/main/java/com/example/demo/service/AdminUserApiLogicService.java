package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.ifs.CrudInterface;

import com.example.demo.model.network.response.*;
import com.example.demo.repository.AdminUserRepository;
import com.example.demo.model.entity.AdminUser;
import com.example.demo.model.enumclass.UserStatus;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.*;

@Service
public class AdminUserApiLogicService implements CrudInterface<AdminUserApiRequest, AdminUserApiResponse> {
	@Autowired
	private AdminUserRepository adminUserRepository;
	
	@Override
	public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {
		AdminUserApiRequest body = request.getData();
		
		AdminUser adminUser = AdminUser.builder()
				.account(body.getAccount())
				.password(body.getPassword())
				.status(UserStatus.REGISTERED)
				.role(body.getRole())
				.lastLoginAt(body.getLastLoginAt())
				.loginFailCount(body.getLoginFailCount())
				.passwordUpdatedAt(body.getPasswordUpdatedAt())
				.registeredAt(body.getRegisteredAt())
				.unregisteredAt(body.getUnregisteredAt())
				.build();
		
		AdminUser newAdminUser = adminUserRepository.save(adminUser);
		
		return response(newAdminUser);
	}

	@Override
	public Header<AdminUserApiResponse> read(Long id) {
		return adminUserRepository.findById(id)
				.map(this::response)
				.orElseGet(()->Header.Error("No Data"));
	}

	@Override
	public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {
		AdminUserApiRequest body = request.getData();
		
		return adminUserRepository.findById(body.getId())
				.map(adminUser->{
					adminUser.setAccount(body.getAccount())
					.setPassword(body.getPassword())
					.setStatus(body.getStatus())
					.setRole(body.getRole())
					.setLastLoginAt(body.getLastLoginAt())
					.setLoginFailCount(body.getLoginFailCount())
					.setPasswordUpdatedAt(body.getPasswordUpdatedAt())
					.setRegisteredAt(body.getRegisteredAt())
					.setUnregisteredAt(body.getUnregisteredAt())
					;
					return adminUser;
				})
				.map(changeAdminUser -> adminUserRepository.save(changeAdminUser))
				.map(this::response)
				.orElseGet(()->Header.Error("No Data"));
				
		
	}

	@Override
	public Header delete(Long id) {
		return adminUserRepository.findById(id)
				.map(adminUser ->{
					adminUserRepository.delete(adminUser);
					return Header.OK("");
				})
				.orElseGet(()->Header.Error("No Data"));
				
	}
	
	private Header<AdminUserApiResponse> response(AdminUser adminUser) {
		AdminUserApiResponse body = AdminUserApiResponse.builder()
				.id(adminUser.getId())
				.account(adminUser.getAccount())
				.password(adminUser.getPassword())
				.status(adminUser.getStatus())
				.role(adminUser.getRole())
				.lastLoginAt(adminUser.getLastLoginAt())
				.loginFailCount(adminUser.getLoginFailCount())
				.passwordUpdatedAt(adminUser.getPasswordUpdatedAt())
				.registeredAt(adminUser.getRegisteredAt())
				.unregisteredAt(adminUser.getUnregisteredAt())
				.build();
		
		return Header.OK(body);
	}
}
