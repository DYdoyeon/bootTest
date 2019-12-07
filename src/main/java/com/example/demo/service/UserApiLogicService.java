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
		// 1. data

		UserApiRequest userApiRequest = request.getData();

		// 2. id -> user 데이터를 찾음.
		Optional<User> optional = userRepository.findById(userApiRequest.getId());
		return optional.map(user -> {
			// 3. data ->update
			user.setAccount(userApiRequest.getAccount()).setPassword(userApiRequest.getPassword())
					.setStatus(userApiRequest.getStatus()).setPhoneNumber(userApiRequest.getPhoneNumber())
					.setEmail(userApiRequest.getEmail()).setRegisteredAt(userApiRequest.getRegisteredAt())
					.setUnregisteredAt(userApiRequest.getUnregisteredAt());
			return user;
		}).map(user -> userRepository.save(user)) // update ->newUser
				.map(user -> response(user)) // userApiResponse
				.orElseGet(() -> Header.Error("데이터 없음 "));

	}

	@Override
	public Header delete(Long id) {
		// 1. id -> repository -> user
		Optional<User> optional = userRepository.findById(id);

		// 2. repository - >delete
		return optional.map(user -> {
			userRepository.delete(user);

			return Header.OK();

		}).orElseGet(() -> Header.Error("데이터 없음 "));

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
