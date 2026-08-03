package com.example.shopping.management.Dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminProductResponseDto {


	@NotEmpty
	private String name;
	
	@NotEmpty
	private String description;
	
	
	private String brand;
	
	
	private String category;
	

	private Double wrongprice;
	

	private Double price;
	
	
	private String imageUrl;
	

	private LocalDateTime createdAt;
	

//	private LocalDateTime updateAt;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public Double getWrongprice() {
		return wrongprice;
	}


	public void setWrongprice(Double wrongprice) {
		this.wrongprice = wrongprice;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
//
//
//	public LocalDateTime getUpdateAt() {
//		return updateAt;
//	}
//
//
//	public void setUpdateAt(LocalDateTime updateAt) {
//		this.updateAt = updateAt;
//	}
	
	
	
}
