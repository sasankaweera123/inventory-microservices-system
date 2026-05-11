package com.sasanka.product_service.controller;

import com.sasanka.product_service.model.Product;
import com.sasanka.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product p) {
        return service.save(p);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @PutMapping("/{id}/reduce")
    public String reduceStock(@PathVariable Long id,
                              @RequestParam int qty) {

        service.reduceStock(id, qty);

        return "Stock updated successfully";
    }

}
