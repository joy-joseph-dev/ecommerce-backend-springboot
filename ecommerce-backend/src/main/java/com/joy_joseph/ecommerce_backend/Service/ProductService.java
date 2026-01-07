package com.joy_joseph.ecommerce_backend.Service;

import com.joy_joseph.ecommerce_backend.Model.Product;
import com.joy_joseph.ecommerce_backend.Repository.ProductRepo;
import com.joy_joseph.ecommerce_backend.dto.ProductRequestDTO;
import com.joy_joseph.ecommerce_backend.dto.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ProductService {
    private ProductRepo repo;

    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProduct(long id) {
        return repo.findById(id).orElse(null);
    }

    public ProductResponseDTO addProduct(ProductRequestDTO dto) {

        Product product1 = new Product();
        product1.setPrice(dto.getPrice());
        product1.setName(dto.getName());
        product1.setBrand(dto.getBrand());
        product1.setCategory(dto.getCategory());
        product1.setDescription(dto.getDescription());
        product1.setImageUrl(dto.getImageUrl());
        product1.setCreatedAt(new Date());
        Product saveProduct= repo.save(product1);

        ProductResponseDTO response = new ProductResponseDTO();
        response.setId(saveProduct.getId());
        response.setName(saveProduct.getName());
        response.setDescription(saveProduct.getDescription());
        response.setBrand(saveProduct.getBrand());
        response.setCategory(saveProduct.getCategory());
        response.setPrice(saveProduct.getPrice());
        response.setImageUrl(saveProduct.getImageUrl());
        response.setCreatedAt(saveProduct.getCreatedAt());
        return response;

    }

    public Product updateProduct(Product product) {
        Product product1 = repo.save(product);
        return product1;
    }

    public void deleteProduct(long id) {
        repo.deleteById(id);
    }

    public List<Product> getAllProductsByKeyword(String keyword) {
        return repo.getAllProductsByKeyword(keyword);
    }

    public Page<Product> getAllProducts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return repo.findAll(pageable);
    }
}