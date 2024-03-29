package com.example.demo.model.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.model.enumclass.OrderType;
import com.example.demo.model.enumclass.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // order_detail
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = { "user", "orderDetailList" })
@Builder
@Accessors(chain = true)
public class OrderGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	private String status;
	
	@Enumerated(EnumType.STRING)
	private OrderType orderType;
	private String revAddress;
	private String revName;
	private String paymentType;
	private BigDecimal totalPrice;
	private Integer totalQuantity;

	private LocalDateTime arrivalDate;
	private LocalDateTime orderAt;

	

	@CreatedDate
	private LocalDateTime createdAt;
	
	@CreatedBy
	private String createdBy;
	
	@LastModifiedDate
	private LocalDateTime updatedAt;
	
	@LastModifiedBy
	private String updatedBy;
//	private Long userId;
	// OrderGroup N : 1 User
	@ManyToOne
	private User user;

	//OrderGroup 1 : N OrderDetail
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderGroup")
	private List<OrderDetail> orderDetailList;

}
