package com.joy_joseph.ecommerce_backend.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Entity
@Table(name="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
     private long id;
    @ManyToOne
     private User user;
     private BigDecimal totalAmount;
     String status ;
     private Date createdAt;
}
