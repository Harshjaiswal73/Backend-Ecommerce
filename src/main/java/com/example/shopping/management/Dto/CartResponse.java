package com.example.shopping.management.Dto;

public class CartResponse {

	private Long productId;
	
	private String productname;
	
	private Double price;
	
	private String imageUrl;
	
	private Integer quantity;

	public CartResponse(Long productId, String productname, Double price, String imageUrl, Integer quantity) {
		super();
		this.productId = productId;
		this.productname = productname;
		this.price = price;
		this.imageUrl = imageUrl;
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	
	
}
