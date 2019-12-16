package com.example.demo.controller.api;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import com.example.demo.controller.CrudController;
import com.example.demo.controller.ifs.CrudInterface;
import com.example.demo.model.entity.OrderGroup;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.response.OrderGroupApiResponse;
import com.example.demo.service.OrderGroupApiLogicService;
import com.example.demo.model.network.request.OrderGroupApiRequest;

@Slf4j
@RestController
@RequestMapping("api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse,OrderGroup> {
	

}
