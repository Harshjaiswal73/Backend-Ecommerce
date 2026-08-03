package com.example.shopping.management.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.management.Entity.Order;
import com.example.shopping.management.Entity.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order>findByUser(User user);

}
