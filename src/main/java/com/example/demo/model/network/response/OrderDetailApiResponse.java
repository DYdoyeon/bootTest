package com.example.demo.model.network.response;

import java.math.BigDecimal;
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
public class OrderDetailApiResponse {
	private Long id;

	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	private Integer quantity;
	
	private BigDecimal totalPrice;
	
	private Long orderGroupId;

	private LocalDateTime arrivalDate;
	private Long itemId;
	
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private String createdBy;
	private String updatedBy;
}
