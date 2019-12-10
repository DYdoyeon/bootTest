package com.example.demo.model.network.response;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.example.demo.model.enumclass.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminUserApiResponse {
	private Long id;
	private String account;
	private String password;
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	private String role;
	private LocalDateTime lastLoginAt;
	private LocalDateTime passwordUpdatedAt;
	private LocalDateTime registeredAt;
	private LocalDateTime unregisteredAt;
	private LocalDateTime createdAt;
	private Integer loginFailCount;
	private LocalDateTime updatedAt;

	private String createdBy;
	private String updatedBy;

}
