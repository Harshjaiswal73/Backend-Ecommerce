package com.example.shopping.management.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.management.Dto.AdminOrderResponseDto;
import com.example.shopping.management.Dto.DashboardStatsDto;
import com.example.shopping.management.Dto.UpdateOrderStatusDto;
import com.example.shopping.management.Entity.Order;
import com.example.shopping.management.Entity.User;
import com.example.shopping.management.Repository.AdminProductRepository;
import com.example.shopping.management.Repository.OrderRepository;
import com.example.shopping.management.Repository.UserRepository;
@Service
public class AdminService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdminProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private void orderRepository(OrderRepository orderRepository) {
		this.orderRepository= orderRepository;
	}
	
	// total user and product
	public DashboardStatsDto TotalProductUserOrders() {
			
			long user = userRepository.count();
			long AdminProduct = productRepository.count();
			long order = orderRepository.count();
			return new DashboardStatsDto(user, AdminProduct,order);
	}
	
	// Get All Users
	public List<User>getAllUsers(){
		List<User>users = userRepository.findAll();
		return users;
	}
	
	// Get All Orders
	public List<AdminOrderResponseDto> getAllOrders() {
	    List<Order>orders = orderRepository.findAll();
	    
	    return orders.stream().map(order ->{
	    	List<String> productNames = order.getOrderItems()
	    								.stream()
	    								.map(item -> item.getProduct().getName())
	    								.toList();
	    return new AdminOrderResponseDto(
	    		order.getId(),
	    		order.getOrderDate(),
	    		order.getTotalAmount(),
	    		order.getOrderStatus(),
	    		order.getUser().getId(),
	    		order.getUser().getEmail(),
	    		productNames
	    		);	
	    }).toList();
	}
	
	public String updateOrderStatus(Long orderId, UpdateOrderStatusDto dto) {
		
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
	    order.setOrderStatus(dto.getOrderStatus());
	    orderRepository.save(order);
	    
	    return "Order update successfully";
	}
}