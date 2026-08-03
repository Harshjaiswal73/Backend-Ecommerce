package com.example.shopping.management.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration {

	private JWTfilter jwTfilter;
	
	public SecurityConfiguration(JWTfilter jwtFilter) {
		this.jwTfilter = jwtFilter;
	}
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
        	.cors(cors -> {})
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            	.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
            	.requestMatchers("/api/cart/**").permitAll()
                .requestMatchers("/api/upload/**").permitAll()
                .requestMatchers("/userprofile/**").permitAll()
                .requestMatchers("/api/order/**").permitAll()
                .requestMatchers("/admin/pannel/**").hasRole("ADMIN")
                .anyRequest().authenticated()
//            		.anyRequest().permitAll()
            		)
        .addFilterBefore(jwTfilter, UsernamePasswordAuthenticationFilter.class);
            
        return http.build();
    }
}