package com.example.demo.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String account;
	private String status;
	private String password;
	private LocalDateTime registeredAt;

	private LocalDateTime unregisteredAt;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	private String email;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_at")
	private LocalDateTime updateAt;

	@Column(name = "updated_by")
	private String updateBy;

}
