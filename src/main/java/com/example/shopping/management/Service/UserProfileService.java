package com.example.shopping.management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.shopping.management.Dto.RegisterRequest;
import com.example.shopping.management.Dto.UserProfileResponseDto;
import com.example.shopping.management.Entity.User;
import com.example.shopping.management.Repository.UserRepository;

@Service
public class UserProfileService {

	@Autowired
	private UserRepository userRepository;
	
	// user profile show
	public UserProfileResponseDto getProfile() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth == null || !auth.isAuthenticated()||auth.getName().equals("anonymousUser")) {
			throw new RuntimeException("Please Login First");
		}
		
		String mobilenumber = auth.getName();
		
		User user = userRepository.findByMobileNumber(mobilenumber).orElseThrow(()-> new RuntimeException("user not found"));
		
		UserProfileResponseDto dto = new UserProfileResponseDto();
		dto.setId(user.getId());
		dto.setFirstname(user.getFirstname());
		dto.setLastname(user.getLastname());
		dto.setMobileNumber(user.getMobileNumber());
		dto.setEmail(user.getEmail());
		dto.setCity(user.getCity());
		dto.setAddress(user.getAddress());
		dto.setPincode(user.getPincode());
		dto.setState(user.getState());
		return dto;
	}
	
	// user profile update
	public User updateProfile(RegisterRequest request) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth == null || !auth.isAuthenticated() || auth.getName().equals("anonymousUser")) {
			throw new RuntimeException("Please login first");
		}
		
		String mobilenumber = auth.getName();
		
		User user = userRepository.findByMobileNumber(mobilenumber).orElseThrow(()-> new RuntimeException("User not found"));
		
		user.setFirstname(request.getFirstname());
		user.setLastname(request.getLastname());
		user.setEmail(request.getEmail());
		user.setAddress(request.getAddress());
		user.setCity(request.getCity());
		user.setPincode(request.getPincode());
		user.setState(request.getState());
		user.setMobileNumber(request.getMobileNumber());
		
		return userRepository.save(user);
	}
	
	// User account delete 
	
	public void deleteprofile(Long id) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String mobilenumber = auth.getName();
	
		User user = userRepository.findByMobileNumber(mobilenumber).orElseThrow(
				()-> new RuntimeException("user not exists"));
		
		userRepository.deleteById(id);
	}
}
