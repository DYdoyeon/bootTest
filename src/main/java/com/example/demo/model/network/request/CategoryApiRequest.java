package com.example.demo.model.network.request;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryApiRequest {
	private Long id;
	
	private String type;
	
	private String title;
	
	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private String createdBy;
	private String updatedBy;
}
