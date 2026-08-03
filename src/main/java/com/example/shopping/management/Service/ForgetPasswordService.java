package com.example.shopping.management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.shopping.management.Entity.User;
import com.example.shopping.management.Repository.UserRepository;

@Service
public class ForgetPasswordService {

	@Autowired
	private UserRepository userRepository;
	
	public User userfind() {
		
		// check user login or not
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth == null || !auth.isAuthenticated() || auth.getName().equals("anonymousUser")) {
			throw new RuntimeException("Please login first");
		}
		
		String email = auth.getName();
		// find email 
		return userRepository.findByEmail(auth.getName())
				.orElseThrow(() -> new RuntimeException("user not found"));
		
		// genrate otp 
		
		// send otp
		
		// verify otp
		
		
	}
}
