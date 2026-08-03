package com.example.shopping.management.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping.management.Entity.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User>findByMobileNumber(String mobileNumber);
	Optional<User> findByEmail(String email);
	boolean existsByMobileNumber(String mobileNumber);
//	void delete();
//	User deleteUser();
	
}
