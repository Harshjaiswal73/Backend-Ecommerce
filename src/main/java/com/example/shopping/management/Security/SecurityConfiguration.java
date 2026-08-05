//package com.example.shopping.management.Security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//public class SecurityConfiguration {
//
//	private JWTfilter jwTfilter;
//	
//	public SecurityConfiguration(JWTfilter jwtFilter) {
//		this.jwTfilter = jwtFilter;
//	}
//	
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//        	.cors(cors -> {})
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//            	.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .requestMatchers("/api/auth/**").permitAll()
//            	.requestMatchers("/api/cart/**").permitAll()
//                .requestMatchers("/api/upload/**").permitAll()
//                .requestMatchers("/userprofile/**").permitAll()
//                .requestMatchers("/api/order/**").permitAll()
//                .requestMatchers("/admin/pannel/**").hasRole("ADMIN")
//                .anyRequest().authenticated()
////            		.anyRequest().permitAll()
//            		)
//        .addFilterBefore(jwTfilter, UsernamePasswordAuthenticationFilter.class);
//            
//        return http.build();
//    }
//}

package com.example.shopping.management.Security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
public class SecurityConfiguration {


    private final JWTfilter jwtFilter;


    public SecurityConfiguration(JWTfilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())


            .authorizeHttpRequests(auth -> auth

                // root
                .requestMatchers("/", "/error").permitAll()


                // preflight request
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()


                // authentication
                .requestMatchers("/api/auth/**").permitAll()


                // public APIs
                .requestMatchers("/api/upload/**").permitAll()
                .requestMatchers("/userprofile/**").permitAll()
                .requestMatchers("/api/cart/**").permitAll()
                .requestMatchers("/api/order/**").permitAll()


                // admin
                .requestMatchers("/admin/pannel/**")
                .hasAnyAuthority("ADMIN", "ROLE_ADMIN")


                // everything else
                .anyRequest().authenticated()
            )


            .addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
            );


        return http.build();
    }



    @Bean
    public CorsConfigurationSource corsConfigurationSource() {


        CorsConfiguration configuration = new CorsConfiguration();


        configuration.setAllowedOrigins(
                List.of(
                    "http://localhost:5173",
                    "http://localhost:3000",
                    "https://ecommerce-frontend-jbgj.vercel.app/"
                )
        );


        configuration.setAllowedMethods(
                List.of(
                    "GET",
                    "POST",
                    "PUT",
                    "DELETE",
                    "OPTIONS"
                )
        );


        configuration.setAllowedHeaders(
                List.of("*")
        );


        configuration.setAllowCredentials(true);



        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();


        source.registerCorsConfiguration(
                "/**",
                configuration
        );


        return source;
    }

}