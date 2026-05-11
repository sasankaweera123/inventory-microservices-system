package com.sasanka.order_service.model;

import lombok.Getter;

@Getter
public class Product {
    private Long id;

    private String name;

    private double price;
    private int stock;
}
