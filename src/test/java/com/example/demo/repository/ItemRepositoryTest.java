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

	//@Test
	public void create() {
		Item item = new Item();

		item.setName("노트");
		item.setPrice(100000);
		item.setContent("삼성노트");

		Item newItem = itemRepository.save(item);
		Assert.assertNotNull(newItem);
	}

	@Test
	public void read() {
		Long id = 1L;
	
		Optional<Item> item = itemRepository.findById(id);
		Assert.assertTrue(item.isPresent());
		/*
		item.ifPresent(i->{
			System.out.println(i);
		});*/
	}
}
