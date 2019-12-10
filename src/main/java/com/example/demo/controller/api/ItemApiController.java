package com.example.demo.controller.api;

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
import com.example.demo.model.network.request.ItemApiRequest;
import com.example.demo.model.network.response.ItemApiResponse;
import com.example.demo.service.ItemApiLogicService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/item")
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

	@Autowired
	private ItemApiLogicService itemApiLogicService;

	@Override
	@PostMapping("") // api/item
	public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
		log.info("creage : ", request);
		return itemApiLogicService.create(request);
	}

	@Override
	@GetMapping("{id}") // api/item/1...
	public Header<ItemApiResponse> read(@PathVariable Long id) {

		return itemApiLogicService.read(id);
	}

	@Override
	@PutMapping("") // api/item
	public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
		// TODO Auto-generated method stub
		return itemApiLogicService.update(request);
	}
	
	@Override
	@DeleteMapping("{id}") // api/item/1...
	public Header delete(@PathVariable Long id) {

		return itemApiLogicService.delete(id);
	}

}
