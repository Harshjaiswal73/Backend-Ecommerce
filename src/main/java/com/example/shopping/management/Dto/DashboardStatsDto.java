package com.example.shopping.management.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStatsDto {

	private long totalUsers;
	private long totalProducts;
	private long totalOrders;	
}
