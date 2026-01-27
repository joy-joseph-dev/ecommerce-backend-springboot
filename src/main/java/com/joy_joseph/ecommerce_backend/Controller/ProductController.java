package com.joy_joseph.ecommerce_backend.Controller;
import com.joy_joseph.ecommerce_backend.Model.Product;
import com.joy_joseph.ecommerce_backend.Service.ProductService;
import com.joy_joseph.ecommerce_backend.dto.ProductRequestDTO;
import com.joy_joseph.ecommerce_backend.dto.ProductResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173/")
@RestController
public class ProductController {
    ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public Product getProductById(@PathVariable long id) {
        return service.getProduct(id);
    }

    @PostMapping("/product")
    public ProductResponseDTO addProduct(@RequestBody ProductRequestDTO dto) {
        return service.addProduct(dto);
    }

    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product) {
        Product product1 = service.updateProduct(product);
        return product1;
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable long id) {
        service.deleteProduct(id);
    }

    @GetMapping("/products/search")
    public List<Product> searchWithKeyword(@RequestParam String keyword) {
        List<Product> products = service.getAllProductsByKeyword(keyword);
        return products;
    }

    @GetMapping("/products/pagination")
    public Page<Product> getAllProductsWithPagination(@RequestParam int page,@RequestParam int size,
                                                      @RequestParam String sortBy) {
        return service.getAllProducts(page, size, sortBy);
    }
}