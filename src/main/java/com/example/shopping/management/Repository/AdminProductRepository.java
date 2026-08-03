package com.example.shopping.management.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.shopping.management.Entity.AdminProduct;

@Repository
public interface AdminProductRepository extends JpaRepository<AdminProduct, Long> {

//	AdminProduct findbyproductnameandIgnorecase(String name);

//	Product findByProductNameIgnorecase();

//	Optional<Product> findByProductNameIgnorecase(String name);

	@Query("""
			SELECT p FROM AdminProduct p
			WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
			OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%'))
			OR LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))
			""")
			List<AdminProduct> searchProducts(@Param("keyword") String keyword);

}
