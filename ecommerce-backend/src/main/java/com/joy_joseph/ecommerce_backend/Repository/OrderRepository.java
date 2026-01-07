package com.joy_joseph.ecommerce_backend.Repository;

import com.joy_joseph.ecommerce_backend.Model.Order;
import com.joy_joseph.ecommerce_backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {


    List<Order> findByUser(User user);
}
