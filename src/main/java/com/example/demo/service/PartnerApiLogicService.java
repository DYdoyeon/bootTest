package com.example.demo.service;

import com.example.demo.model.network.response.*;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.PartnerRepository;
import com.example.demo.model.entity.Partner;
import com.example.demo.model.enumclass.UserStatus;
import com.example.demo.model.network.Header;
import com.example.demo.model.network.request.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.ifs.CrudInterface;

@Service
public class PartnerApiLogicService implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {
	@Autowired
	private PartnerRepository partnerRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
		//1. data 
		PartnerApiRequest body = request.getData();
		
		//2. data -> repository
		Partner partner = Partner.builder()
				.name(body.getName())
				.status(UserStatus.REGISTERED)
				.address(body.getAddress())
				.callCenter(body.getCallCenter())
				.partnerNumber(body.getPartnerNumber())
				.businessNumber(body.getBusinessNumber())
				.ceoName(body.getCeoName())
				.registeredAt(body.getRegisteredAt())
				.unregisteredAt(body.getUnregisteredAt())
				.category(categoryRepository.getOne(body.getCategoryId()))
				.build();
		Partner newPartner = partnerRepository.save(partner);
		
		return response(newPartner);
	}

	@Override
	public Header<PartnerApiResponse> read(Long id) {
		return partnerRepository.findById(id)
				.map(this::response)
				.orElseGet(()->Header.Error("No Data"));
		
	}

	@Override
	public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
		//1. data
		PartnerApiRequest body = request.getData();
		
		//2. update
		return partnerRepository.findById(body.getId())
				.map(partner->{
					partner
					.setStatus(body.getStatus())
					.setName(body.getName())
					.setAddress(body.getAddress())
					.setCallCenter(body.getCallCenter())
					.setPartnerNumber(body.getPartnerNumber())
					.setBusinessNumber(body.getBusinessNumber())
					.setCeoName(body.getCeoName())
					.setRegisteredAt(body.getRegisteredAt())
					.setUnregisteredAt(body.getUnregisteredAt())
					.setCategory(categoryRepository.getOne(body.getCategoryId()))
					;
					
					return partner;
				})
				.map(changePartner ->partnerRepository.save(changePartner))
				.map(this::response)
				.orElseGet(()->Header.Error("No Data"));
	}

	@Override
	public Header delete(Long id) {
		return partnerRepository.findById(id)
		.map(partner ->{
			partnerRepository.delete(partner);
			return Header.OK("");
		})
		.orElseGet(()->Header.Error("No Data"));
		
		
	}

	private Header<PartnerApiResponse> response(Partner partner) {
		PartnerApiResponse body = PartnerApiResponse.builder()
				.id(partner.getId())
				.name(partner.getName())
				.status(partner.getStatus())
				.address(partner.getAddress())
				.callCenter(partner.getCallCenter())
				.partnerNumber(partner.getPartnerNumber())
				.businessNumber(partner.getBusinessNumber())
				.ceoName(partner.getCeoName())
				.registeredAt(partner.getRegisteredAt())
				.unregisteredAt(partner.getUnregisteredAt())
				.categoryId(partner.getCategory().getId())
				.build();
		
		return Header.OK(body);
		
	}
}
