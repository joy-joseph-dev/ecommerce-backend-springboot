package com.joy_joseph.ecommerce_backend.Service;

import com.joy_joseph.ecommerce_backend.Model.Cart;
import com.joy_joseph.ecommerce_backend.Model.Order;
import com.joy_joseph.ecommerce_backend.Model.User;
import com.joy_joseph.ecommerce_backend.Repository.*;
import com.joy_joseph.ecommerce_backend.exception.BadRequestException;
import com.joy_joseph.ecommerce_backend.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {

            private UserRepository userRepository;
            private CartRepository cartRepository;
            private OrderRepository orderRepository;
            private CartItemRepository cartItemRepository;


    public OrderService(UserRepository userRepository, CartRepository cartRepository,
                        OrderRepository orderRepository, CartItemRepository cartItemRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.cartItemRepository=cartItemRepository;


    }
    @Transactional
    public Order placeOrder(long userId) {
        //get user
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));


        //fetch cart
        //check cart is not empty
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            throw new ResourceNotFoundException("Cart not found");
        }
        if (cart.getTotalPrice() <= 0) {
            throw new BadRequestException("Cart is empty");
        }
        //create order
        Order order = new Order();
        order.setUser(user);
        order.setTotalAmount(BigDecimal.valueOf(cart.getTotalPrice()));
        order.setStatus("PLACED");
        order.setCreatedAt(new Date());

        //save order
        Order saveOrder = orderRepository.save(order);

        //clear cart
        cartItemRepository.deleteByCart(cart);
        cart.setTotalPrice(0);
        cartRepository.save(cart);
        return saveOrder;

    }

    public List<Order> getOrdersOfUser(long userId) {
       User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
       return orderRepository.findByUser(user);
    }
}

