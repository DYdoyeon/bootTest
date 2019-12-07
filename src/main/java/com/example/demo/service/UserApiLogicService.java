package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.ifs.CrudInterface;
import com.example.demo.model.entity.User;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.UserApiRequest;
import com.example.demo.model.network.response.UserApiResponse;
import com.example.demo.repository.UserRepository;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {
	@Autowired
	private UserRepository userRepository;

	// 1. Request Data
	// 2. user 생성
	// 3. 생성된 데이터 -> userApiResponse return
	@Override
	public Header<UserApiResponse> create(Header<UserApiRequest> request) {
		// 1. Request Data
		UserApiRequest userApiRequest = request.getData();

		// 2. user 생성
		User user = User.builder().account(userApiRequest.getAccount()).password(userApiRequest.getPassword())
				.status("REGISTERED").phoneNumber(userApiRequest.getPhoneNumber()).email(userApiRequest.getEmail())
				.registeredAt(LocalDateTime.now()).build();

		User newUser = userRepository.save(user);

		// 3. 생성된 데이터 -> userApiResponse return

		return response(newUser);
	}

	@Override
	public Header<UserApiResponse> read(Long id) {
		// id -> repository getOne, getById
		// Optional<User> optional = userRepository.findById(id);

		// user -> userApiReponse return

		// return optional.map(user -> response(user)).orElseGet(() -> Header.Error("데이터
		// 없음 "));
		return userRepository.findById(id).map(user -> response(user)).orElseGet(() -> Header.Error("데이터 없음 "));

	}

	@Override
	public Header<UserApiResponse> update(Header<UserApiRequest> request) {

		return null;
	}

	@Override
	public Header delete(Long id) {

		return null;
	}

	private Header<UserApiResponse> response(User user) {
		// user -> userApiResponse

		UserApiResponse userApiResponse = UserApiResponse.builder().id(user.getId()).account(user.getAccount())
				.password(user.getPassword()).email(user.getEmail()).phoneNumber(user.getPhoneNumber())
				.status(user.getStatus()).registeredAt(user.getRegisteredAt()).registeredAt(user.getUnregisteredAt())
				.build();

		// Header + data return
		return Header.OK(userApiResponse);
	}

}