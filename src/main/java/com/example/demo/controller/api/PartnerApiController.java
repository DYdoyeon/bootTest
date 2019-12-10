package com.example.demo.controller.api;

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

import com.example.demo.model.network.response.*;
import com.example.demo.service.PartnerApiLogicService;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.*;
import com.example.demo.controller.ifs.CrudInterface;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("api/partner")
public class PartnerApiController implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {

	@Autowired
	private PartnerApiLogicService partnerApiLogicService;

	@Override
	@PostMapping("")
	public Header<PartnerApiResponse> create(@RequestBody Header<PartnerApiRequest> request) {

		return partnerApiLogicService.create(request);
	}

	@Override
	@GetMapping("{id}")
	public Header<PartnerApiResponse> read(@PathVariable(name = "id") Long id) {

		return partnerApiLogicService.read(id);
	}

	@Override
	@PutMapping("")
	public Header<PartnerApiResponse> update(@RequestBody Header<PartnerApiRequest> request) {

		return partnerApiLogicService.update(request);
	}

	@Override
	@DeleteMapping("{id}")
	public Header delete(@PathVariable Long id) {
		return partnerApiLogicService.delete(id);
	}

}
