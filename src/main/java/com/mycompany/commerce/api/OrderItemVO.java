package com.mycompany.commerce.api;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderItemVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "Item ID is required")
    private long itemId;

    @NotNull(message = "Item Quanity is required")
    private int quantity;
}