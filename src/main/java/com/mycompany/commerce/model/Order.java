package com.mycompany.commerce.model;

import java.util.List;
import lombok.Data;

@Data
public class Order {

    private List<OrderItem> orderItems;
    private String totalCost;
}