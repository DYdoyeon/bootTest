package com.example.demo.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Assert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.*;
import com.example.demo.model.entity.Item;
import com.example.demo.model.entity.User;

public class ItemRepositoryTest extends BootjarTestApplicationTests {

	@Autowired
	private ItemRepository itemRepository;

//	@Test
	public void create() {
		Item item = new Item();

		item.setName("UNREGISTERED");
		item.setPrice(9000000);
		item.setTitle("삼성노트북 A100");
		item.setContent("2019년형 노트입니다.");
		item.setName("삼성 노트북");
		item.setBrandName("samsung");
		item.setRegisteredAt(LocalDateTime.now());
		item.setCreatedAt(LocalDateTime.now());
		item.setCreatedBy("Partner01");
		item.setPartnerId(1L);
		Item newItem = itemRepository.save(item);
		Assert.assertNotNull(newItem);
	}
 
	// @Test
	public void read() {
		Long id = 1L;

		Optional<Item> item = itemRepository.findById(id);
		Assert.assertTrue(item.isPresent());
		/*
		 * item.ifPresent(i->{ System.out.println(i); });
		 */
	}
}
