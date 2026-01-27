package com.joy_joseph.ecommerce_backend.Controller;

import com.joy_joseph.ecommerce_backend.Model.Order;
import com.joy_joseph.ecommerce_backend.Model.User;
import com.joy_joseph.ecommerce_backend.Service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173/")
@RestController
@RequestMapping("/orders")
public class OrderController {
    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/place/{userId}")
    public Order placeOrder(@PathVariable long userId){
        return orderService.placeOrder(userId);
    }
    @GetMapping("/user/{userId}")
   public List<Order> getOrdersOfUser(@PathVariable long userId){
        return orderService.getOrdersOfUser(userId);

    }
}
