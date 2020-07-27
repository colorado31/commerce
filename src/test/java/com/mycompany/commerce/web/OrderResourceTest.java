package com.mycompany.commerce.web;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycompany.commerce.api.ItemVO;
import com.mycompany.commerce.api.OrderItemVO;
import com.mycompany.commerce.api.OrderVO;
import com.mycompany.commerce.web.v1.ItemResource;
import com.mycompany.commerce.web.v1.OrderResource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OrderResourceTest {

    @Resource
    private ItemResource itemResource;
    
    @Resource
    private OrderResource orderResource;

    @Test
    public void orderItems() throws Exception {

        // Save Orange item
        ItemVO orange = new ItemVO();
        orange.setName("Orange");
        orange.setPrice(1.75);
        orange.setDiscount(0.20);
        orange = itemResource.saveItem(orange);

        // Find Orange item
        ItemVO persistedOrange = itemResource.getItem(orange.getId());
        assertNotNull(persistedOrange);
        assertEquals("Orange", persistedOrange.getName());
        assertEquals(1.75, persistedOrange.getPrice());
        assertEquals(0.20, persistedOrange.getDiscount());

        // Create Order
        OrderItemVO orderItemVO = new OrderItemVO();
        orderItemVO.setItemId(persistedOrange.getId());
        orderItemVO.setQuantity(3);
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderItems(Arrays.asList(orderItemVO));

        // Submit order
        orderVO = orderResource.submit(orderVO);
        assertNotNull(orderVO);
        assertTrue(orderVO.getOrderItems().size() == 1);
        assertEquals("4.20", orderVO.getTotalCost());
    }
}
