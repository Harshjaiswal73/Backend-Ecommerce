package com.example.shopping.management.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.shopping.management.Dto.CartRequest;
import com.example.shopping.management.Dto.CartResponse;
import com.example.shopping.management.Entity.Cart;
import com.example.shopping.management.Entity.AdminProduct;
import com.example.shopping.management.Entity.User;
import com.example.shopping.management.Enum.ProductStock;
import com.example.shopping.management.Repository.CartRepository;
import com.example.shopping.management.Repository.AdminProductRepository;
import com.example.shopping.management.Repository.UserRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdminProductRepository productRepository;
	
	
	public void addtocart(CartRequest cartRequest) {
		
		// user authenticated hai ya nhi vo check kiya
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println("Auth = " + auth);
		System.out.println("Name = " + auth.getName());
		System.out.println("Authenticated = " + auth.isAuthenticated());
		
		//check condition
		if (auth == null || !auth.isAuthenticated()|| auth.getName().equals("anonymousUser")) {
			throw new RuntimeException("Please login first");
		}
		// logged in user
		String mobilenumber = auth.getName();
		
		// user ko find kerna using mobilenumber
		User user = userRepository.findByMobileNumber(mobilenumber)
				.orElseThrow(()-> new RuntimeException("User not found"));
		
		// product find kerna using id
		Long productid = cartRequest.getProduct().getId();
		AdminProduct product = productRepository.findById(productid)
				.orElseThrow(()-> new RuntimeException("product not found"));
		
		// cart me user and product hai ya nhi 
		Optional<Cart>existing = cartRepository.findByUserAndProduct(user, product);
		if (existing.isPresent()) {
			Cart cart = existing.get();
			cart.setQuantity(cart.getQuantity()+1);
			cartRepository.save(cart);
		}else {
			Cart cart = new Cart();
			cart.setUser(user);
			cart.setProduct(product);
			cart.setQuantity(cartRequest.getQuantity());
			cartRepository.save(cart);
		}
		
		
	}
	
	// remove cart item
	public void deletecartitem(Long productId) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth==null || !auth.isAuthenticated()||auth.getName().equals("anonymousUser")) {
			throw new RuntimeException("Please login first");
		}
		
		String mobilenumber = auth.getName();
		cartRepository.deleteById(productId);
		
	}
	
	public List<CartResponse>getcart(Long userId){
		List<Cart>carts = cartRepository.findByUserId(userId);
		
		return carts.stream().map(cart ->{
			AdminProduct product = cart.getProduct();
			
			
			return new CartResponse(
			    product.getId(),        
			    product.getName(),      
			    product.getPrice(),     
			    product.getImageUrl(),  
			    cart.getQuantity()     
			);
		}).toList();
	}	
	
	// checkout page 
	public List<Cart> checkout() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println(auth);
		System.out.println(auth.getName());
		
		if(auth==null || !auth.isAuthenticated() || auth.getName().equals("anonymousUser")) {
			throw new RuntimeException("please login first");
		}
		
		String mobilenumber = auth.getName();
		
	    User user = userRepository.findByMobileNumber(mobilenumber).orElseThrow(()-> new RuntimeException("user not found"));
	    
		List<Cart>carts = cartRepository.findByUser(user);
		
		if(carts.isEmpty()) {
			throw new RuntimeException("cart is empty");
		}
		return carts;
	}
}