package com.example.demo.model.network;

import java.time.LocalDateTime;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Header<T> {

	// @JsonProperty("transaction_time")
	private LocalDateTime transactionTime;
	private String resultCode;
	private String description;

	private T data;

	// OK
	public static <T> Header<T> OK() {
		return (Header<T>) Header.builder().transactionTime(LocalDateTime.now()).resultCode("OK").description("OK")
				.build();
	}

	// Data OK
	// OK
	public static <T> Header<T> OK(T data) {
		return (Header<T>) Header.builder().transactionTime(LocalDateTime.now()).resultCode("OK").description("OK")
				.data(data).build();
	}

	// error
	public static <T> Header<T> Error(String description) {
		return (Header<T>) Header.builder().transactionTime(LocalDateTime.now()).resultCode("ERROR")
				.description(description).build();
	}
}
