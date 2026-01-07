package com.joy_joseph.ecommerce_backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Cart cart;
    private int quantity;
    private int price;
    @ManyToOne
    private Product product;


    public int getPrice(CartItem cartItem) {
        return this.price;
    }
}
