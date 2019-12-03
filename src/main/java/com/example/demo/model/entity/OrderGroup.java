package com.example.demo.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // order_detail
@ToString(exclude = {"user"})
public class OrderGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String status;
	private String orderType;
	private String revAddress;
	private String revName;
	private String paymentType;
	private BigDecimal totalPrice;
	private Integer totalQuantity;

	private LocalDateTime arrivalDate;
	private LocalDateTime orderAt;

	private LocalDateTime createdAt;
	private String createdBy;
	private LocalDateTime updatedAt;
	private String updatedBy;
	//private Long userId;
	
	// OrderGroup N : 1 User
	@ManyToOne
	private User user;

	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderDetailList")
//	private List<OrderDetail> orderDetailList;
}
