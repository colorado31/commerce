package com.mycompany.commerce.web.v1;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.commerce.api.OrderItemVO;
import com.mycompany.commerce.api.OrderVO;
import com.mycompany.commerce.exception.ResourceNotFoundException;
import com.mycompany.commerce.model.Order;
import com.mycompany.commerce.model.OrderItem;
import com.mycompany.commerce.service.IOrderService;

@RestController
@RequestMapping("v1/order")
public class OrderResource {

    @Resource
    private IOrderService orderService;

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public OrderVO submit(@RequestBody OrderVO orderVO) throws ResourceNotFoundException {
        Order order = new Order();
        order.setOrderItems(new ArrayList<OrderItem>());
        if (!CollectionUtils.isEmpty(orderVO.getOrderItems())) {
            for (OrderItemVO orderItemVO : orderVO.getOrderItems()) {
                OrderItem orderItem = new OrderItem();
                BeanUtils.copyProperties(orderItemVO, orderItem);
                order.getOrderItems().add(orderItem);
            }
        }
        order = orderService.submit(order);
        BeanUtils.copyProperties(order, orderVO);
        orderVO.setOrderItems(new ArrayList<OrderItemVO>());
        if (!CollectionUtils.isEmpty(order.getOrderItems())) {
            for (OrderItem orderItem : order.getOrderItems()) {
                OrderItemVO orderItemVO = new OrderItemVO();
                BeanUtils.copyProperties(orderItem, orderItemVO);
                orderVO.getOrderItems().add(orderItemVO);
            }
        }
        return orderVO;
    }
}
