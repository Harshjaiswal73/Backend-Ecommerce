package com.example.shopping.management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.management.Dto.MyOrderResponseDto;
import com.example.shopping.management.Dto.PlaceOrderResponseDto;
import com.example.shopping.management.Dto.UpdateOrderStatusDto;
import com.example.shopping.management.Service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/place-order")
	public ResponseEntity<PlaceOrderResponseDto>placeorder(){
		return ResponseEntity.ok(orderService.placeOrder());
	}
	
	@GetMapping("/myorders")
	public ResponseEntity<List<MyOrderResponseDto>>myorders(){
		return ResponseEntity.ok(orderService.getallorders());
	}
	
	
}
