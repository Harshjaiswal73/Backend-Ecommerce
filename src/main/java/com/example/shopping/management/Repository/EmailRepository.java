package com.example.shopping.management.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping.management.Entity.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

}
