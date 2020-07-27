package com.mycompany.commerce.model;

import lombok.Data;

@Data
public class OrderItem {

    private long itemId;
    private int quantity;
}