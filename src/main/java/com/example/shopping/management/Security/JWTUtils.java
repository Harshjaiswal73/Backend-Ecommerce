package com.example.shopping.management.Security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtils {

	private final  SecretKey Key;
	private final long expirationTokenMS = 1000L * 60 * 60 * 24;
	
	public JWTUtils() {
		String secret = System.getenv("JWT_SECRET");
		if (secret == null || secret.isEmpty()) {
			secret = "MySuperSecureJwtSecretKeyForShoppingApp2026";
		}
		Key = Keys.hmacShaKeyFor(secret.getBytes());
	}
	
	public String genratetoken(String subject,String role) {
		
		Date now = new Date();
		Date expirationdate = new Date(now.getTime()+expirationTokenMS);
		
		return Jwts.builder()
				.setSubject(subject)
				.claim("role",role)
				.setIssuedAt(now)
				.setExpiration(expirationdate)
				.signWith(Key,SignatureAlgorithm.HS256)
				.compact();
	}
	
	public String extractRole(String token) {
		
		return Jwts.parserBuilder()
				.setSigningKey(Key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.get("role",String.class);
	}
	
	public boolean validateToken(String token) {
		
		try {
			Jwts.parserBuilder().setSigningKey(Key).build().parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			// TODO: handle exception
			return false;
		}
		
	}
	public String getSubject(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(Key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	public String extractUsername(String token) {
	    return getSubject(token);
	}
	
}
