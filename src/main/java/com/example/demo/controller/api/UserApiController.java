package com.example.demo.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.ifs.CrudInterface;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.UserApiRequest;
import com.example.demo.model.network.response.UserApiResponse;
import com.example.demo.service.UserApiLogicService;

@Slf4j
@RestController
@RequestMapping("api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

	@Autowired
	private UserApiLogicService userApiLogicService;

	@Override
	@PostMapping("")
	public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
		log.info("{}", request);
		// TODO Auto-generated method stub
		return userApiLogicService.create(request);
	}

	@Override
	@GetMapping("{id}") // api/user/{id}
	public Header<UserApiResponse> read(@PathVariable(name = "id") Long id) {
		log.info("read id : : {}", id);
		return userApiLogicService.read(id);
			}
	// public Header update() {

	@PutMapping("")
	@Override
	public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@DeleteMapping("{id}")
	public Header delete(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	// }
}
