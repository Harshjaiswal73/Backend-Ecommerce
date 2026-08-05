package com.example.shopping.management.Dto;

import java.time.LocalDateTime;
import java.util.List;

import com.example.shopping.management.Enum.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminOrderResponseDto {

	private long orderId;
	
	private LocalDateTime orderDate;
	
	private Double totalAmount;
	
	private OrderStatus status;
	
	private Long userId;
	
	private String userEmail;
	
	private List<String>productNames;		
}
