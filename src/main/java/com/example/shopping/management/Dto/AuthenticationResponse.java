package com.example.shopping.management.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

	private String token;
	private String message;
	private String firstname;
	private Long userId;
	private String role;
}
