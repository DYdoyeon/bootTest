package com.example.demo.model.network.response;

import java.time.LocalDateTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.example.demo.model.enumclass.UserStatus;

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
	

	@Enumerated(EnumType.STRING)
	private UserStatus status;
	private String phoneNumber;
	private LocalDateTime registeredAt;
	private LocalDateTime unRegisteredAt;

}
