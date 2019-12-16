package com.example.demo.model.network.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.example.demo.model.enumclass.OrderDetailStatus;
import com.example.demo.model.enumclass.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailApiRequest {
	private Long id;

	@Enumerated(EnumType.STRING)
	private OrderDetailStatus status;
	
	private Integer quantity;
	
	private BigDecimal totalPrice;
	
	private LocalDateTime arrivalDate;
	
	private Long itemId;
	private Long orderGroupId;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String createdBy;
	private String updatedBy;
}
