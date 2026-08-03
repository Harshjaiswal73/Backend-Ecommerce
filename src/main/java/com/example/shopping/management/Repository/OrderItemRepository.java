package com.example.shopping.management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping.management.Entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
