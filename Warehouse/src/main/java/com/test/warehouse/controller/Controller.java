package com.test.warehouse.controller;

import com.test.warehouse.domain.Item;
import com.test.warehouse.exception.EntityWasNotFoundException;
import com.test.warehouse.service.ItemService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@Log4j2
public class Controller {

    private final ItemService itemService;

    @Autowired
    public Controller(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(path = "/items/{id}")
    public Item checkProductAvailability(@PathVariable(name = "id") Long itemId) throws EntityWasNotFoundException {
        log.debug("retrieveWarehouseItem has been called");
        return itemService.retrieveItem(itemId);
    }
}
