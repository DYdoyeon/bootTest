package com.example.demo.model.network.request;

import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiRequest {
	private Long id;
	private String account;
	private String password;
	private String status;
	private String email;
	private String phoneNumber;
	private LocalDateTime registeredAt;
	private LocalDateTime unregisteredAt;
}
