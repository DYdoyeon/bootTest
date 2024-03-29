package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.controller.ifs.CrudInterface;
import com.example.demo.model.entity.OrderDetail;
import com.example.demo.model.entity.OrderGroup;
import com.example.demo.model.enumclass.OrderType;
import com.example.demo.model.enumclass.UserStatus;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.*;
import com.example.demo.model.network.response.*;
import com.example.demo.repository.OrderGroupRepository;
import com.example.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderGroupApiLogicService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
		//
		OrderGroupApiRequest body = request.getData();

		OrderGroup orderGroup = OrderGroup.builder()
				.status(body.getStatus())
				.orderType(OrderType.ALL)
				.revAddress(body.getRevAddress())
				.revName(body.getRevName())
				.paymentType(body.getPaymentType())
				.totalPrice(body.getTotalPrice())
				.totalQuantity(body.getTotalQuantity())
				.orderAt(body.getOrderAt())
				.arrivalDate(body.getArrivalDate())
				.user(userRepository.getOne(body.getUserId()))
				.build();
		OrderGroup newOrderGroup = baseRepository.save(orderGroup);

		return response(newOrderGroup);

	}

	@Override
	public Header<OrderGroupApiResponse> read( Long id) {
		Optional<OrderGroup> optional = baseRepository.findById(id);

		log.info("hedaer : "+baseRepository.findById(id));
		
		return baseRepository.findById(id)
				// .map(this::response)
				.map(orderGroup -> response(orderGroup)).orElseGet(() -> Header.Error("데이터 없음"));

	}

	@Override
	public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
		OrderGroupApiRequest body = request.getData();

		return baseRepository.findById(body.getId()).map(orderGroup -> {
			orderGroup.setStatus(body.getStatus())
					.setOrderType(body.getOrderType())
					.setRevAddress(body.getRevAddress())
					.setRevName(body.getRevName())
					.setPaymentType(body.getPaymentType())
					.setTotalPrice(body.getTotalPrice())
					.setTotalQuantity(body.getTotalQuantity())
					.setOrderAt(body.getOrderAt())
					.setArrivalDate(body.getArrivalDate())
					.setUser(userRepository.getOne(body.getUserId()));
			return orderGroup;
		}).map(changeOrderGroup -> baseRepository.save(changeOrderGroup)).map(this::response)
				.orElseGet(() -> Header.Error("데이터 없음"));

	}

	@Override
	public Header delete(Long id) {
		return baseRepository.findById(id).map(orderGroup -> {
			baseRepository.delete(orderGroup);
			return Header.OK("");
		}).orElseGet(() -> Header.Error("데이터 없음"));

	}

	private Header<OrderGroupApiResponse> response(OrderGroup orderGroup) {
		OrderGroupApiResponse body = OrderGroupApiResponse.builder()
				.id(orderGroup.getId())
				.status(orderGroup.getStatus())
				.orderType(orderGroup.getOrderType())
				.revAddress(orderGroup.getRevAddress())
				.revName(orderGroup.getRevName())
				.paymentType(orderGroup.getPaymentType())
				.totalPrice(orderGroup.getTotalPrice())
				.totalQuantity(orderGroup.getTotalQuantity())
				.orderAt(orderGroup.getOrderAt())
				.arrivalDate(orderGroup.getArrivalDate())
				.userId(orderGroup.getUser().getId())
				.build();

		return Header.OK(body);

	}
}
