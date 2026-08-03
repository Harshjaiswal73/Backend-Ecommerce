package com.example.shopping.management.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shopping.management.Dto.AuthenticationResponse;
import com.example.shopping.management.Dto.LoginResponseDto;
import com.example.shopping.management.Dto.RegisterRequest;
import com.example.shopping.management.Entity.AdminProduct;
import com.example.shopping.management.Entity.User;
import com.example.shopping.management.Enum.Role;
import com.example.shopping.management.Repository.AdminProductRepository;
import com.example.shopping.management.Repository.UserRepository;
import com.example.shopping.management.Security.JWTUtils;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdminProductRepository productRepository;
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private JWTUtils jwtUtils;
	
	public AuthenticationResponse registeruser(RegisterRequest request) {
		
		if (request.getMobileNumber()==null || request.getPassword()==null) {
			throw new RuntimeException("mobilenumber and password is required");
		}
		
		Optional<User>existing = userRepository.findByMobileNumber(request.getMobileNumber());
		if (existing.isPresent()) {
			throw new RuntimeException("user already exists");
		}
		
		User user = new User();
		
		user.setFirstname(request.getFirstname());
		user.setLastname(request.getLastname());
		user.setMobileNumber(request.getMobileNumber());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setAddress(request.getAddress());
		user.setCity(request.getCity());
		user.setState(request.getState());
		user.setPincode(request.getPincode());
		user.setCreatedAt(LocalDateTime.now());
		user.setRole(Role.USER);
		
		userRepository.save(user);
		
		String token = jwtUtils.genratetoken(request.getMobileNumber(),Role.USER.name());
		
		AuthenticationResponse response = new AuthenticationResponse();
		response.setToken(token);
		response.setFirstname(user.getFirstname());
		
		return response;
		
	}
	
	public AuthenticationResponse loginuser(LoginResponseDto dto) {
		User user = userRepository.findByMobileNumber(dto.getMobileNumber()).orElseThrow(()-> new RuntimeException("User Not Found"));
		
		if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid credential");
		}
		
	    String token = jwtUtils.genratetoken(user.getMobileNumber(),user.getRole().name());
	    AuthenticationResponse response = new AuthenticationResponse();
	    response.setToken(token);
	    response.setFirstname(user.getFirstname());
	    response.setUserId(user.getId());
	    response.setRole(user.getRole().name());
	    return response;
	}
	
    public List<AdminProduct> searchProducts(String keyword){
    	return productRepository.searchProducts(keyword);
    }
	
}
