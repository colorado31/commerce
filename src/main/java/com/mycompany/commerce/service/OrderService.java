package com.mycompany.commerce.service;

import java.text.DecimalFormat;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.mycompany.commerce.exception.ResourceNotFoundException;
import com.mycompany.commerce.model.Item;
import com.mycompany.commerce.model.Order;
import com.mycompany.commerce.model.OrderItem;

@Service
@Transactional
public class OrderService implements IOrderService {

    @Resource
    private ItemService itemService;

    @Override
    public Order submit(Order order) throws ResourceNotFoundException {
        if (!CollectionUtils.isEmpty(order.getOrderItems())) {
            double cost = 0D;
            for (OrderItem orderItem : order.getOrderItems()) {
                Item persistedItem = itemService.getItem(orderItem.getItemId());
                cost += calculatePrice(persistedItem, orderItem.getQuantity());
            }
            DecimalFormat df = new DecimalFormat("#.00");
            order.setTotalCost(df.format(cost));
        }
        return order;
    }

    private Double calculatePrice(Item item, int quantity) {
        return item.getPrice() * (1-item.getDiscount()) * quantity;
    }
}