package com.joy_joseph.ecommerce_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    private String name;
    private String description;
    private int price;
    private String brand;
    private String category;
    private String imageUrl;

}
