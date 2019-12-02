package com.example.demo.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Assert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.*;
import com.example.demo.model.entity.*;

public class OrderDetailRepositoryTest extends BootjarTestApplicationTests {

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Test
	public void create() {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderAt(LocalDateTime.now());

		orderDetail.setUserId(7L);

		orderDetail.setItemId(1L);
		OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
		Assert.assertNotNull(orderDetail);
	}
}
