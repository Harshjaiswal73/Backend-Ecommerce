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
	
	

	public AdminOrderResponseDto(long orderId, LocalDateTime orderDate, Double totalAmount, OrderStatus status,
			Long userId, String userEmail, List<String> productNames) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.userId = userId;
		this.userEmail = userEmail;
		this.productNames = productNames;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<String> getProductNames() {
		return productNames;
	}

	public void setProductNames(List<String> productNames) {
		this.productNames = productNames;
	}
	
	
}
