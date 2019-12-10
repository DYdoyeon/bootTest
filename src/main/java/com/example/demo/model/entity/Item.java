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

import com.example.demo.model.enumclass.ItemStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = { "orderDetailList", "partner" })
@EntityListeners(AuditingEntityListener.class)
@Builder
@Accessors(chain = true)
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private BigDecimal price;
	private String content;
	private String title;
	private String brandName;

	@Enumerated(EnumType.STRING)
	private ItemStatus status;
	//등록 / 해지 / 검수중(등록대기중) 
	@CreatedDate
	private LocalDateTime createdAt;

	@CreatedBy
	private String createdBy;

	@LastModifiedDate
	private LocalDateTime updatedAt;

	@LastModifiedBy
	private String updatedBy;
	private LocalDateTime registeredAt;
	private LocalDateTime unregisteredAt;

	// Item N : 1 Partner
	@ManyToOne
	private Partner partner;

	// EAGER = 1:1 / OneToMany : 1:N
	// LAZY = SELECT * From item where id= ?
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
	private List<OrderDetail> orderDetailList;
}
