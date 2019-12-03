package com.example.demo.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer price;
	private String content;
	private String title;
	private String brandName;
	private String status;
	private Long partnerId;
	private LocalDateTime createdAt;
	private String createdBy;
	private LocalDateTime updatedAt;
	private String updatedBy;
	private LocalDateTime registeredAt;
	private LocalDateTime unregisteredAt;

	// EAGER = 1:1 / OneToMany : 1:N
	 //LAZY = SELECT * From item where id= ?
//	 @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
//	 private List<OrderDetail> orderDetailList;
}
