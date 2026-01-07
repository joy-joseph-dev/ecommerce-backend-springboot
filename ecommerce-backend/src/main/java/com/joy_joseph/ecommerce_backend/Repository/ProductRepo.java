package com.joy_joseph.ecommerce_backend.Repository;

import com.joy_joseph.ecommerce_backend.Model.Product;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    @Query(" Select p from Product p WHERE " +
    "LOWER (p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
    "LOWER (p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
    "LOWER (p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
    "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> getAllProductsByKeyword(@Param("keyword") String keyword);
}
