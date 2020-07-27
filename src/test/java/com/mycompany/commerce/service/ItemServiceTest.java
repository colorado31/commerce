package com.mycompany.commerce.service;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mycompany.commerce.exception.ValidationException;
import com.mycompany.commerce.model.Item;
import com.mycompany.commerce.persist.ItemRepository;

@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    private Item item;
    private final String ITEM_NAME = "Orange";
    private final double ITEM_PRICE = 2.50D;

    @Before
    public void setup() {
        item = new Item();
        item.setName(ITEM_NAME);
        item.setPrice(ITEM_PRICE);
    }

    @Test
    public void defaultDiscount() {
        when(itemRepository.save(item)).thenReturn(item);

        try {
            item = itemService.save(item);
            assertEquals(0, item.getDiscount());
            verify(itemRepository).save(item);
        } catch (ValidationException ve) {
            fail("ValidationException should not have been thrown: " + ve.getMessage());
        }
    }

    @Test
    public void validateItem() {
        Item mockItem = new Item();
        mockItem.setId(1L);
        mockItem.setName(ITEM_NAME);
        mockItem.setPrice(ITEM_PRICE);
        when(itemRepository.save(item)).thenReturn(mockItem);

        try {
            itemService.save(item);
            verify(itemRepository).save(item);
        } catch (ValidationException ve) {
            fail("ValidationException should not have been thrown: " + ve.getMessage());
        }
    }

    @Test
    public void validateItemWithMissingName() {
        item.setName(null);

        try {
            itemService.save(item);
            fail("ValidationException should have been thrown");
        } catch (ValidationException ve) {
            assertEquals("Item Name required", ve.getMessage());
            verify(itemRepository, times(0)).save(item);
        }
    }

    @Test
    public void validateItemWithMissingPrice() {
        item.setPrice(null);
        try {
            itemService.save(item);
            fail("ValidationException should have been thrown");
        } catch (ValidationException ve) {
            assertEquals("Item Price required", ve.getMessage());
            verify(itemRepository, times(0)).save(item);
        }
    }
}
