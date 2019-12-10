package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.*;
import com.example.demo.model.network.response.*;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.PartnerRepository;
import com.example.demo.controller.ifs.CrudInterface;
import com.example.demo.model.entity.Item;
import com.example.demo.model.entity.User;
import com.example.demo.model.enumclass.ItemStatus;
import com.example.demo.model.enumclass.UserStatus;

@Service
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

	@Autowired
	private PartnerRepository partnerRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
		ItemApiRequest body = request.getData();
		// optional -> null check

		Item item = Item.builder()
				.status(ItemStatus.REGISTERED)
				.name(body.getName())
				.title(body.getTitle())
				.content(body.getContent())
				.price(body.getPrice())
				.brandName(body.getBrandName())
				.registeredAt(LocalDateTime.now())
				.partner(partnerRepository.getOne(body.getPartnerId()))
				.build();

		Item newItem = itemRepository.save(item);
		return response(newItem);
	}

	@Override
	public Header<ItemApiResponse> read(Long id) {

		
		return itemRepository.findById(id)
				.map(item -> response(item))
				.orElseGet(() -> Header.Error("데이터 없음"));
	}

	@Override
	public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
		//1. search data
		ItemApiRequest body = request.getData();
		
		return itemRepository.findById(body.getId())
			.map(entityItem -> {
				entityItem
				.setStatus(body.getStatus())
				.setName(body.getName())
				.setTitle(body.getTitle())
				.setContent(body.getContent())
				.setPrice(body.getPrice())
				.setBrandName(body.getBrandName())
				.setRegisteredAt(body.getRegisteredAt())
				.setUnregisteredAt(body.getUnregisteredAt());
				
				return entityItem;
			})
			.map(newEntityItem -> itemRepository.save(newEntityItem))
			.map(item -> response(item))
			.orElseGet(()->Header.Error("데이터 없음"));
		
	}

	@Override
	public Header delete(Long id) {
		/*return itemRepository.findById(id)
			.map(item -> {
				itemRepository.delete(item);
				return Header.OK();
			})
			.orElseGet(()->Header.Error("데이터 없음"));
		*/
		// 1. id -> repository -> user
		Optional<Item> optional = itemRepository.findById(id);

		// 2. repository - >delete
		return optional.map(user -> {
			itemRepository.delete(user);

			return Header.OK();

		}).orElseGet(() -> Header.Error("데이터 없음 "));
		
		
		
	}

	private Header<ItemApiResponse> response(Item item) {
		ItemApiResponse body = ItemApiResponse.builder()
				.id(item.getId())
				.status(item.getStatus())
				.name(item.getName())
				.title(item.getTitle())
				.content(item.getContent())
				.price(item.getPrice())
				.brandName(item.getBrandName())
				.registeredAt(item.getRegisteredAt())
				.unregisteredAt(item.getUnregisteredAt())
				.partnerId(item.getPartner().getId())
				.build();
		return Header.OK(body);
	}

}
