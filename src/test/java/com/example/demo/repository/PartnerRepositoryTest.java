package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.*;
import com.example.demo.model.entity.Partner;
import com.example.demo.model.entity.User;

public class PartnerRepositoryTest extends BootjarTestApplicationTests {

	@Autowired
	private PartnerRepository partnerRepository;

	@Test
	public void create() {
		String name = "Partner01";
		String status = "REGISTERED";
		String address = "서울시 강남구";
		String callCenter = "010-1111-1111";
		String partnerNumber = "010-1111-1111";
		String ceoName = "홍길동";
		LocalDateTime registedAt = LocalDateTime.now();
		LocalDateTime createdAt = LocalDateTime.now();
		String createdBy = "AdminServer";
		Long categoryId = 1L;
		Partner partner = new Partner();
		partner.setName(name);
		partner.setStatus(status);
		partner.setAddress(address);
		partner.setCallCenter(callCenter);
		partner.setCeoName(ceoName);
		partner.setPartnerNumber(partnerNumber);
		partner.setRegisteredAt(registedAt);
		partner.setCreatedAt(createdAt);
		partner.setCreatedBy(createdBy);
		partner.setCategoryId(categoryId);
		
		Partner newPartner = partnerRepository.save(partner);
		Assert.assertNotNull(newPartner);
		Assert.assertEquals(newPartner.getName(), name);
	}
}
