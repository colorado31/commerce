package com.mycompany.commerce.api;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ItemVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "Item Name is required")
    private String name;

    @NotNull(message = "Item Price is required")
    private Double price;

    @Min(value = 0, message = "Discount must be between 0 and 1")
    @Max(value = 1, message = "Discount must be between 0 and 1")
    private Double discount;
}