package com.mycompany.commerce.service;

import com.mycompany.commerce.exception.ResourceNotFoundException;
import com.mycompany.commerce.exception.ValidationException;
import com.mycompany.commerce.model.Item;

public interface IItemService {

    public Iterable<Item> getAllItems();

    public Item getItem(long id) throws ResourceNotFoundException;

    public Item save(Item item) throws ValidationException;
}