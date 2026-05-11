package com.sasanka.order_service.client;

import com.sasanka.order_service.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryClient {

    @Autowired
    private RestTemplate restTemplate;

    private final String PRODUCT_SERVICE_URL =
            "http://PRODUCT-SERVICE/products/";

    public Product getProduct(Long id) {

        return restTemplate.getForObject(
                PRODUCT_SERVICE_URL + id,
                Product.class
        );
    }

    public void reduceStock(Long id, int qty) {

        restTemplate.put(
                PRODUCT_SERVICE_URL + id + "/reduce?qty=" + qty,
                null
        );
    }
}