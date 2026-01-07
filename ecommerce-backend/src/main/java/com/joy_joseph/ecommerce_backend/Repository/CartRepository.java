package com.joy_joseph.ecommerce_backend.Repository;

import com.joy_joseph.ecommerce_backend.Model.Cart;

import com.joy_joseph.ecommerce_backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findByUser(User user);
}
