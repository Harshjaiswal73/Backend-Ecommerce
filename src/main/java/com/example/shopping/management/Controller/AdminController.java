package com.example.shopping.management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.management.Dto.AdminOrderResponseDto;
import com.example.shopping.management.Dto.DashboardStatsDto;
import com.example.shopping.management.Dto.UpdateOrderStatusDto;
import com.example.shopping.management.Entity.Order;
import com.example.shopping.management.Entity.User;
import com.example.shopping.management.Service.AdminService;
import com.example.shopping.management.Service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/pannel")
@RequiredArgsConstructor
@CrossOrigin(origins= "http://localhost:3000")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	
	@GetMapping("/TotalUserProduct")
	public ResponseEntity<DashboardStatsDto>totalproduct(){
		 DashboardStatsDto stats = adminService.TotalProductUserOrders();
		 return ResponseEntity.ok(stats);
	}
	
	@GetMapping("/AllUsers")
	public ResponseEntity<List<User>>getallusers(){
		List<User> user = adminService.getAllUsers();
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/AllOrders")
	public ResponseEntity<List<AdminOrderResponseDto>>getAllOrders(){
		return ResponseEntity.ok(adminService.getAllOrders());
	}
	
	@PutMapping("/orderStatusUpdate/{orderId}")
	public ResponseEntity<String>orderstatusupdate(@PathVariable Long orderId,
			@RequestBody UpdateOrderStatusDto dto){
		 System.out.println(dto.getOrderStatus());
		return ResponseEntity.ok(adminService.updateOrderStatus(orderId, dto));
	}
	

	
}