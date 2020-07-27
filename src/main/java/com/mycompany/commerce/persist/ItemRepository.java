package com.mycompany.commerce.persist;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.commerce.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {
}