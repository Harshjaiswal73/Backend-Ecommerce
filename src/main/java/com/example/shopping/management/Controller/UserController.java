package com.example.shopping.management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.shopping.management.Dto.AuthenticationResponse;
import com.example.shopping.management.Dto.LoginResponseDto;
import com.example.shopping.management.Dto.RegisterRequest;
import com.example.shopping.management.Entity.AdminProduct;
import com.example.shopping.management.Service.ProductUploadService;
import com.example.shopping.management.Service.UserService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductUploadService productUploadService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> registeruser(@RequestBody RegisterRequest registerRequest){
		AuthenticationResponse Token = userService.registeruser(registerRequest);
		return ResponseEntity.ok(Token);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> loginuser(@RequestBody LoginResponseDto dto){
		AuthenticationResponse Token = 	userService.loginuser(dto);
		return ResponseEntity.ok(Token);	
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<AdminProduct>>getproducts(){
		List<AdminProduct> products = productUploadService.getAllProduct();
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<AdminProduct>> searchProducts(
			@RequestParam String keyword){
		return ResponseEntity.ok(userService.searchProducts(keyword));
	}
	
}
