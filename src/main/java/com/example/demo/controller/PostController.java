package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.example.demo.model.SearchParam;

@RestController
@RequestMapping("/api")
 public class PostController {

	@PostMapping("/postMethod")
	public String postMethod(@RequestBody SearchParam searchParam) {
		return "OK"; 
	}
}
