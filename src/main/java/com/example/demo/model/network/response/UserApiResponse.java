package com.example.demo.model.network.response;

import java.time.LocalDateTime;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiResponse {
	private Long id;
	private String account;
	private String password;
	private String email;
	private String status;
	private String phoneNumber;
	private LocalDateTime registeredAt;
	private LocalDateTime unRegisteredAt;

}
