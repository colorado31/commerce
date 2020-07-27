package com.mycompany.commerce.web;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycompany.commerce.api.ItemVO;
import com.mycompany.commerce.web.v1.ItemResource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ItemResourceTest {

    @Resource
    private ItemResource itemResource;

    @Test
    public void saveItems() throws Exception {

        // Save Apple item
        ItemVO apple = new ItemVO();
        apple.setName("Apple");
        apple.setPrice(2.50);
        apple = itemResource.saveItem(apple);

        // Find Apple item
        ItemVO persistedApple = itemResource.getItem(apple.getId());
        assertNotNull(persistedApple);
        assertEquals("Apple", persistedApple.getName());
        assertEquals(2.50, persistedApple.getPrice());
        assertEquals(0, persistedApple.getDiscount());

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

        // Find all
        List<ItemVO> itemList = itemResource.getAllItems();
        assertNotNull(itemList);
        assertTrue(itemList.size() == 2);
    }

}
