package com.mycompany.commerce.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mycompany.commerce.exception.ResourceNotFoundException;
import com.mycompany.commerce.exception.ValidationException;
import com.mycompany.commerce.model.Item;
import com.mycompany.commerce.persist.ItemRepository;

@Service
@Transactional
public class ItemService implements IItemService {

    @Resource
    private ItemRepository itemRepository;

    @Override
    public Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItem(long id) throws ResourceNotFoundException {
        return itemRepository
          .findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Item not found"));
    }

    @Override
    public Item save(Item item) throws ValidationException {
        if (item.getDiscount() == null) {
            item.setDiscount(0D);
        }
        validate(item);
        return itemRepository.save(item);
    }

    private void validate(Item item) throws ValidationException {
        if (StringUtils.isEmpty(item.getName())) throw new ValidationException("Item Name required");
        if (StringUtils.isEmpty(item.getPrice())) throw new ValidationException("Item Price required");
        if (item.getDiscount() < 0D || item.getDiscount() > 1D) throw new ValidationException("Discount must be between 0 and 1");
    }
}