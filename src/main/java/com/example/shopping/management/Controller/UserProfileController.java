package com.example.shopping.management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping.management.Dto.RegisterRequest;
import com.example.shopping.management.Dto.UserProfileResponseDto;
import com.example.shopping.management.Entity.User;
import com.example.shopping.management.Service.UserProfileService;
import com.example.shopping.management.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/userprofile")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserProfileController {

	@Autowired
	private UserProfileService profileService;
	
	@GetMapping("/showprofile")
	public ResponseEntity<UserProfileResponseDto> getprofile(){
		return ResponseEntity.ok(profileService.getProfile());
	}
	
	@PutMapping("/updateprofile")
	public ResponseEntity<User>updateprofile(@RequestBody RegisterRequest  request){
		return ResponseEntity.ok(profileService.updateProfile(request));
	}
	// not working 
	@DeleteMapping("/deleteprofile/{id}")
	public ResponseEntity<String>deleteprofile(@PathVariable Long id){
		profileService.deleteprofile(id);
		return ResponseEntity.ok("profile deleted successfully");
	}
}
