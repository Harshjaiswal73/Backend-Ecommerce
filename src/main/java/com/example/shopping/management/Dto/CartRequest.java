package com.example.shopping.management.Dto;

import com.example.shopping.management.Entity.AdminProduct;
import com.example.shopping.management.Entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {

	private User user;
	
	private AdminProduct product;
	
	private int quantity;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AdminProduct getProduct() {
		return product;
	}

	public void setProduct(AdminProduct product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}
