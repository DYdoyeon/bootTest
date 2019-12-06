package com.example.demo.repository;

import java.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.*;
import com.example.demo.model.entity.Partner;

public class PartnerRepositoryTest extends BootjarTestApplicationTests {

	@Autowired
	private PartnerRepository partnerRepository;

	@Test
	public void create() {
		String name = "Partner01";
		String status = "REGISTERED";
		String address = "서울시 강남구";
		String callCenter = "070-1111-1111";
		String partnerNumber = "010-1111-2222";
		String businessNumber = "010-1111-2222";
		String ceoName = "홍길동";
		LocalDateTime registedAt = LocalDateTime.now();
		//Long categoryId = 1L;
		Partner partner = new Partner();
		partner.setName(name);
		partner.setStatus(status);
		partner.setAddress(address);
		partner.setCallCenter(callCenter);
		partner.setCeoName(ceoName);
		partner.setPartnerNumber(partnerNumber);
		partner.setBusinessNumber(businessNumber);
		partner.setRegisteredAt(registedAt);

		Partner newPartner = partnerRepository.save(partner);
		Assert.assertNotNull(newPartner);
		Assert.assertEquals(newPartner.getName(), name);
	}
}
