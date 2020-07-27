package com.mycompany.commerce.service;

import com.mycompany.commerce.exception.ResourceNotFoundException;
import com.mycompany.commerce.model.Order;

public interface IOrderService {

    public Order submit(Order order) throws ResourceNotFoundException;
}