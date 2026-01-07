package com.joy_joseph.ecommerce_backend.Repository;

import com.joy_joseph.ecommerce_backend.Model.Cart;
import com.joy_joseph.ecommerce_backend.Model.CartItem;
import com.joy_joseph.ecommerce_backend.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    // CartItemRepository
    CartItem findByCartAndProduct(Cart cart, Product product);
    List<CartItem> findByCart(Cart cart);
    void deleteByCart(Cart cart);
}
