package com.example.demo.controller.api;

import com.example.demo.model.network.response.*;
import com.example.demo.service.AdminUserApiLogicService;
import com.example.demo.model.network.request.*;
import com.example.demo.controller.ifs.CrudInterface;
import com.example.demo.model.network.Header;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/adminUser")
public class AdminUserApiController implements CrudInterface<AdminUserApiRequest, AdminUserApiResponse> {
	

	@Autowired
	private AdminUserApiLogicService adminUserApiLogicService;
	
	
	@Override
	@PostMapping("")
	public Header<AdminUserApiResponse> create(@RequestBody Header<AdminUserApiRequest> request) {
		return adminUserApiLogicService.create(request);
	}

	@Override
	@GetMapping("{id}")
	public Header<AdminUserApiResponse> read(@PathVariable(name = "id") Long id) {
		
		
		return adminUserApiLogicService.read(id); 
				
	}

	@Override
	@PutMapping("")
	public Header<AdminUserApiResponse> update(@RequestBody Header<AdminUserApiRequest> request) {
		
		return adminUserApiLogicService.update(request);
	}

	@Override
	@DeleteMapping("{id}")
	public Header delete(@PathVariable Long id) {
		return adminUserApiLogicService.delete(id);
	}

}
