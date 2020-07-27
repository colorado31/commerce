package com.mycompany.commerce.api;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class OrderVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<OrderItemVO> orderItems;
    private String totalCost;
}