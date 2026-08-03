package com.example.shopping.management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.shopping.management.Dto.CartRequest;
import com.example.shopping.management.Entity.Cart;
import com.example.shopping.management.Service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin(origins ="http://localhost:3000")
public class CartController {

	@Autowired
	private CartService cartService;
	
	
	@PostMapping("/add-to-cart")
	public ResponseEntity<?>addtocart(@RequestBody CartRequest request){
		cartService.addtocart(request);
		return ResponseEntity.ok("add to cart success");
	}
	
	@GetMapping("/get-cart-product/{userId}")
	public ResponseEntity<?>getproductcart(@PathVariable Long userId){
		return ResponseEntity.ok(cartService.getcart(userId));
	}
	
	@DeleteMapping("/remove-cart/{productId}")
	public ResponseEntity<String>deletecartitem(@PathVariable Long productId){
		cartService.deletecartitem(productId);
		return ResponseEntity.ok("product deleted Successfully");
	}
	
	@GetMapping("/checkout")
	public ResponseEntity<List<Cart>> checkout(){
		List<Cart> cart = cartService.checkout();
		return ResponseEntity.ok(cart);
	}
	
}