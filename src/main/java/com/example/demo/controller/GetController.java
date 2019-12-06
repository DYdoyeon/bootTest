package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.slf4j.Logger;

import com.example.demo.model.SearchParam;
import com.example.demo.model.network.Header;

@RestController
@RequestMapping("/api")
public class GetController {
	@RequestMapping(method = RequestMethod.GET, path = "/getMethod")
	public String getRequest() {
		return "Hi getMethod";
	}

	@GetMapping("/getParameter")
	public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd) {
		return id + pwd;

	}

	@GetMapping("/getMultiParameter")
	public SearchParam getMultiParameter(SearchParam searchParam) {
		return searchParam;
	}

	@GetMapping("/header")
	public Header getHeader() {
		
		//{"resultCode" :"OK" , "descripton" : "OK"}
		return Header.builder().resultCode("OK").description("OK").build();

	}
}
