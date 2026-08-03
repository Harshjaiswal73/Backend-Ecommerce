package com.example.shopping.management.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping.management.Entity.Cart;
import com.example.shopping.management.Entity.AdminProduct;
import com.example.shopping.management.Entity.User;


@Repository
public interface CartRepository extends JpaRepository<Cart,Long>{

	Optional<Cart>findByUserAndProduct(User user,AdminProduct product);

	List<Cart> findByUserId(Long userId);

	List<Cart> findByUser(User user);

}
