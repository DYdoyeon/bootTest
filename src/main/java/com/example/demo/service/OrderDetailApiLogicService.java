package com.example.demo.service;

import com.example.demo.model.network.response.*;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderGroupRepository;

import lombok.extern.slf4j.Slf4j;

import com.example.demo.model.entity.OrderDetail;
import com.example.demo.model.enumclass.UserStatus;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.*;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.ifs.CrudInterface;

@Slf4j
@Service
public class OrderDetailApiLogicService implements CrudInterface<OrderDetailApiRequest, OrderDetailApiResponse> {
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private OrderGroupRepository orderGroupRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {
		// 1. Request Data
		OrderDetailApiRequest orderDetailApiRequest = request.getData();

		// 2. OrderDetail 생
		OrderDetail orderDetail = OrderDetail.builder()
				.status(UserStatus.REGISTERED)
				.arrivalDate(orderDetailApiRequest.getArrivalDate())
				.quantity(orderDetailApiRequest.getQuantity())
				.totalPrice(orderDetailApiRequest.getTotalPrice())
				.orderGroup(orderGroupRepository.getOne(orderDetailApiRequest.getOrderGroupId()))
				.item(itemRepository.getOne(orderDetailApiRequest.getItemId())).build();

		// 3. 생성된 데이터 -> OrderDetailResponse Return
		OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
		return response(newOrderDetail);
	}

	@Override
	public Header<OrderDetailApiResponse> read(Long id) {
		Optional<OrderDetail> optional = orderDetailRepository.findById(1L);
		//problem : when id = 1, optional is empty......why/.........TT
	
		
		return optional.map(orderDetail -> response(orderDetail)).orElseGet(() -> Header.Error("No data"));
	}

	@Override
	public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
		// 1. data
		OrderDetailApiRequest orderDetailApiRequest = request.getData();

		// 2. id -> orderdetail

		// Optional<OrderDetail> optional =
		// orderDetailRepository.findById(orderDetailApiRequest.getId());
		// log.info(null, optional.get());
		return orderDetailRepository.findById(orderDetailApiRequest.getId()).map(orderDetail -> {
			orderDetail.setStatus(orderDetailApiRequest.getStatus())
					.setArrivalDate(orderDetailApiRequest.getArrivalDate())
					.setQuantity(orderDetailApiRequest.getQuantity())
					.setTotalPrice(orderDetailApiRequest.getTotalPrice())
					.setOrderGroup(orderGroupRepository.getOne(orderDetailApiRequest.getOrderGroupId()))
					.setItem(itemRepository.getOne(orderDetailApiRequest.getItemId()));

			;
			log.info(" : " + orderDetailApiRequest.getStatus());
			return orderDetail;

		}).map(newOrderDetail -> orderDetailRepository.save(newOrderDetail)).map(this::response)
				.orElseGet(() -> Header.Error("No data"));

	}

	@Override
	public Header delete(Long id) {
		Optional<OrderDetail> optional = orderDetailRepository.findById(id);

		return optional.map(orderDetail -> {
			orderDetailRepository.delete(orderDetail);
			return Header.OK();

		}).orElseGet(() -> Header.Error("No Data"));

	}

	private Header<OrderDetailApiResponse> response(OrderDetail orderDetail) {
		OrderDetailApiResponse body = OrderDetailApiResponse.builder()
				.id(orderDetail.getId())
				.status(orderDetail.getStatus())
				.arrivalDate(orderDetail.getArrivalDate())
				.quantity(orderDetail.getQuantity())
				.totalPrice(orderDetail.getTotalPrice())
				.orderGroupId(orderDetail.getOrderGroup().getId())
				.itemId(orderDetail.getItem().getId()).build();
		log.info("orderDetail.id :" + orderDetail.getId());
		log.info("body.status :" + body.getStatus());
		return Header.OK(body);
	}
}
