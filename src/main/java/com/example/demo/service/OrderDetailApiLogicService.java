package com.example.demo.service;

import com.example.demo.model.network.response.*;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderGroupRepository;

import lombok.extern.slf4j.Slf4j;

import com.example.demo.model.entity.OrderDetail;
import com.example.demo.model.enumclass.OrderDetailStatus;
import com.example.demo.model.enumclass.UserStatus;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.*;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.controller.ifs.CrudInterface;

@Slf4j
@Service
public class OrderDetailApiLogicService extends BaseService<OrderDetailApiRequest, OrderDetailApiResponse,OrderDetail> implements CrudInterface<OrderDetailApiRequest, OrderDetailApiResponse> {
	
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
				.status(OrderDetailStatus.WAITING)
				.arrivalDate(orderDetailApiRequest.getArrivalDate())
				.quantity(orderDetailApiRequest.getQuantity())
				.totalPrice(orderDetailApiRequest.getTotalPrice())
				.orderGroup(orderGroupRepository.getOne(orderDetailApiRequest.getOrderGroupId()))
				.item(itemRepository.getOne(orderDetailApiRequest.getItemId())).build();

		// 3. 생성된 데이터 -> OrderDetailResponse Return
		OrderDetail newOrderDetail = baseRepository.save(orderDetail);
		return response(newOrderDetail);
	}

	@Override
	public Header<OrderDetailApiResponse> read( Long id) {
		Optional<OrderDetail> optional = baseRepository.findById(id);

		log.info("hedaer : "+baseRepository.findById(id));
		
		return  baseRepository.findById(id).map(orderDetail -> response(orderDetail)).orElseGet(() -> Header.Error("No data"));
	}

	@Override
	public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
		// 1. data
		OrderDetailApiRequest orderDetailApiRequest = request.getData();

		// 2. id -> orderdetail

		// Optional<OrderDetail> optional =
		// orderDetailRepository.findById(orderDetailApiRequest.getId());
		// log.info(null, optional.get());
		return baseRepository.findById(orderDetailApiRequest.getId()).map(orderDetail -> {
			orderDetail.setStatus(orderDetailApiRequest.getStatus())
					.setArrivalDate(orderDetailApiRequest.getArrivalDate())
					.setQuantity(orderDetailApiRequest.getQuantity())
					.setTotalPrice(orderDetailApiRequest.getTotalPrice())
					.setOrderGroup(orderGroupRepository.getOne(orderDetailApiRequest.getOrderGroupId()))
					.setItem(itemRepository.getOne(orderDetailApiRequest.getItemId()));

			;
			log.info(" : " + orderDetailApiRequest.getStatus());
			return orderDetail;

		}).map(newOrderDetail -> baseRepository.save(newOrderDetail)).map(this::response)
				.orElseGet(() -> Header.Error("No data"));

	}

	@Override
	public Header delete(Long id) {
		Optional<OrderDetail> optional = baseRepository.findById(id);

		return optional.map(orderDetail -> {
			baseRepository.delete(orderDetail);
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
		return Header.OK(body);
	}
}
