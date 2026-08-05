package com.example.shopping.management.Dto;

import com.example.shopping.management.Enum.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderResponseDto {

	private Long orderId;
	
	private String message;
	
	private OrderStatus orderStatus;
}
