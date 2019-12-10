package com.example.demo.model.network.response;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.example.demo.model.enumclass.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartnerApiResponse {
	private Long id;
	
	private String name;

	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	private String address;

	private String businessNumber;
	private String callCenter;
	
	private String partnerNumber;
	
	private String ceoName;

	private Long categoryId;
	private LocalDateTime registeredAt;

	private LocalDateTime unregisteredAt;
	
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private String createdBy;
	private String updatedBy;
	
	
}
