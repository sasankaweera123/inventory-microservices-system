package com.sasanka.product_service.service;

import com.sasanka.product_service.model.Product;
import com.sasanka.product_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public Product getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product save(Product p) {
        return repo.save(p);
    }

    public void reduceStock(Long id, int qty) {
        Product p = getById(id);
        if (p.getStock() < qty) {
            throw new RuntimeException("Not enough stock");
        }
        p.setStock(p.getStock() - qty);
        repo.save(p);
    }

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

}
