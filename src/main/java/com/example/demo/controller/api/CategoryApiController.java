package com.example.demo.controller.api;

import com.example.demo.model.network.response.*;
import com.example.demo.service.CategoryApiLogicService;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.*;
import com.example.demo.controller.ifs.CrudInterface;

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
@RequestMapping("api/category")
public class CategoryApiController implements CrudInterface<CategoryApiRequest, CategoryApiResponse> {
	@Autowired
	private CategoryApiLogicService categoryApiLogicService;

	@Override
	@PostMapping("")
	public Header<CategoryApiResponse> create(@RequestBody Header<CategoryApiRequest> request) {

		return categoryApiLogicService.create(request);

	}

	@Override
	@GetMapping("{id}")
	public Header<CategoryApiResponse> read(@PathVariable(name = "id") Long id) {
		// TODO Auto-generated method stub
		log.info("category read : {id} "+id);
		return categoryApiLogicService.read(id);
	}

	@Override
	@PutMapping("")
	public Header<CategoryApiResponse> update(@RequestBody Header<CategoryApiRequest> request) {
		// TODO Auto-generated method stub
		return categoryApiLogicService.update(request);
	}

	@Override
	@DeleteMapping("{id}")
	public Header delete(@PathVariable Long id) {
		// TODO Auto-generated method stub
		return categoryApiLogicService.delete(id);
	}

}
