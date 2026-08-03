package com.example.shopping.management.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.shopping.management.Dto.MyOrderResponseDto;
import com.example.shopping.management.Dto.PlaceOrderResponseDto;
import com.example.shopping.management.Dto.UpdateOrderStatusDto;
import com.example.shopping.management.Entity.Cart;
import com.example.shopping.management.Entity.Order;
import com.example.shopping.management.Entity.OrderItem;
import com.example.shopping.management.Entity.User;
import com.example.shopping.management.Enum.OrderStatus;
import com.example.shopping.management.Repository.CartRepository;
import com.example.shopping.management.Repository.OrderItemRepository;
import com.example.shopping.management.Repository.OrderRepository;
import com.example.shopping.management.Repository.UserRepository;

@Service
public class OrderService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	public PlaceOrderResponseDto placeOrder() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth == null || !auth.isAuthenticated()|| auth.getName().equals("anonymousUser")) {
			throw new RuntimeException("please login first");
		}
		
		String mobileNumber = auth.getName();
		
		User user = userRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new RuntimeException("user not found"));
		
		// user ke cart item ko fetch kerna hai
		List<Cart>carts = cartRepository.findByUser(user);
		
		if(carts.isEmpty()) {
			throw new RuntimeException("cart is empty");
		}
		
		double totalAmount = 0;
		
		for (Cart cart : carts) {
			double subtotal = cart.getProduct().getPrice() * cart.getQuantity();
			
			totalAmount += subtotal;
		}
		
		Order order = new Order();
		order.setUser(user);
		order.setTotalAmount(totalAmount);
		order.setOrderStatus(OrderStatus.PENDING);
		order.setOrderDate(LocalDateTime.now());
		
		Order savedOrder = orderRepository.save(order);
		
		// order item create karo
		emailService.sendOrderConfirmationEmail(user, savedOrder);
		
		for (Cart cart : carts) {
			
			//debug
			System.out.println("Product = " + cart.getProduct().getName() + ",Qty = "+ cart.getQuantity());
			
			OrderItem item = new OrderItem();
			item.setOrder(savedOrder);
			item.setProduct(cart.getProduct());
			item.setQuantity(cart.getQuantity());
			item.setPrice(cart.getProduct().getPrice());
			
			item.setSubtotal(
					cart.getProduct().getPrice() * cart.getQuantity()
					);
			orderItemRepository.save(item);
			// debug
			System.out.println("Saved Item = " + item.getProduct().getName());
		}
		
		cartRepository.deleteAll(carts);
		PlaceOrderResponseDto responseDto = new PlaceOrderResponseDto();

		responseDto.setOrderId(savedOrder.getId());
		responseDto.setMessage("Order placed successfully");
		responseDto.setOrderStatus(savedOrder.getOrderStatus());

		return responseDto;
	}
	
	public List<MyOrderResponseDto> getallorders() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth==null || !auth.isAuthenticated()||auth.getName().equals("anonymousUser")) {
			throw new RuntimeException("please login first");
		}
		
		String mobileNumber = auth.getName();
		
		User user = userRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new RuntimeException("user not found"));
		
		List<Order> orders = orderRepository.findByUser(user);
		
		List<MyOrderResponseDto>response = new ArrayList<>();
		for (Order order : orders) {
			for(OrderItem item : order.getOrderItems()) {
				MyOrderResponseDto dto = new MyOrderResponseDto();
				dto.setOrderId(order.getId());
				dto.setProductId(item.getProduct().getId());
				dto.setProductName(item.getProduct().getName());
				dto.setProductImage(item.getProduct().getImageUrl());
				dto.setQuantity(item.getQuantity());
				dto.setPrice(item.getPrice());
				dto.setSubtotal(item.getSubtotal());
				dto.setTotalAmount(order.getTotalAmount());
				dto.setOrderStatus(order.getOrderStatus());
				dto.setPaymentStatus("PAID");// razorpay lagne ke baad yahan entity se aayega
				dto.setOrderDate(order.getOrderDate());
				response.add(dto);
			}
		}
		return response;	
	}
	
}