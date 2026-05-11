package com.sasanka.order_service.service;

import com.sasanka.order_service.client.InventoryClient;
import com.sasanka.order_service.model.Order;
import com.sasanka.order_service.model.Product;
import com.sasanka.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private InventoryClient inventoryClient;

    public Order placeOrder(Order order) {

        Product product =
                inventoryClient.getProduct(order.getProductId());

        if (product.getStock() < order.getQuantity()) {
            throw new RuntimeException("Insufficient stock");
        }

        double total =
                product.getPrice() * order.getQuantity();

        order.setTotalPrice(total);

        order.setStatus("PLACED");

        inventoryClient.reduceStock(
                order.getProductId(),
                order.getQuantity()
        );

        return repo.save(order);
    }
}
