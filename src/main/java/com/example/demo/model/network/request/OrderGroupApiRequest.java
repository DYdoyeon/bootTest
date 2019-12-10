package com.example.demo.model.network.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.example.demo.model.enumclass.OrderType;
import com.example.demo.model.enumclass.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderGroupApiRequest {
	private Long id;

	@Enumerated(EnumType.STRING)
	private OrderType status;

	private String orderType;

	private String revAddress;

	private String revName;

	private String paymentType;

	private BigDecimal totalPrice;

	private Integer totalQuantity;

	private LocalDateTime orderAt;

	private LocalDateTime arrivalDate;

	private Long userId;

}
