package com.example.demo.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.*;
import com.example.demo.model.entity.OrderGroup;
import com.example.demo.model.entity.Partner;
import com.example.demo.model.entity.User;

public class OrderGroupRepositoryTest extends BootjarTestApplicationTests {

	@Autowired
	private OrderGroupRepository orderGroupRepository;

	@Test
	public void create() {
		
		OrderGroup orderGroup = new OrderGroup();
	//	orderGroup.setStatus("COMPLETED");
		orderGroup.setOrderType("CARD");
		orderGroup.setRevAddress("서울시 서초구");
		orderGroup.setRevName("김이사");
		orderGroup.setPaymentType("CARD");
        orderGroup.setTotalQuantity(1);
		orderGroup.setTotalPrice(BigDecimal.valueOf(1000000));
		orderGroup.setOrderAt(LocalDateTime.now().minusDays(2));
		orderGroup.setArrivalDate(LocalDateTime.now());
		//orderGroup.setUserId(1L); // ->User

		OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
		Assert.assertNotNull(newOrderGroup);
	}

}
