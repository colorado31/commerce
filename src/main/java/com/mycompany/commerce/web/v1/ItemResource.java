package com.mycompany.commerce.web.v1;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.commerce.api.ItemVO;
import com.mycompany.commerce.exception.ResourceNotFoundException;
import com.mycompany.commerce.exception.ValidationException;
import com.mycompany.commerce.model.Item;
import com.mycompany.commerce.service.IItemService;

@RestController
@RequestMapping("v1/item")
public class ItemResource {

    @Resource
    private IItemService itemService;
 
    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemVO> getAllItems() {
        List<ItemVO> responseList = new ArrayList<ItemVO>();
        Iterable<Item> items = itemService.getAllItems();
        if (items != null) {
            for (Item item : items) {
                ItemVO itemVO = new ItemVO();
                BeanUtils.copyProperties(item, itemVO);
                responseList.add(itemVO);
            }
        }
        return responseList;
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ItemVO getItem(@PathVariable Long id) throws ResourceNotFoundException {
        Item item = itemService.getItem(id);
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(item, itemVO);
        return itemVO;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ItemVO saveItem(@RequestBody ItemVO itemVO) throws ValidationException {
        Item item = new Item();
        BeanUtils.copyProperties(itemVO, item);
        item = itemService.save(item);
        BeanUtils.copyProperties(item, itemVO);
        return itemVO;
    }
}
