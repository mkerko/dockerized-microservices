package com.test.store.controller;

import com.test.store.exception.EntityWasNotFoundException;
import com.test.store.exception.NotEnoughProductsInWarehouseException;
import com.test.store.service.CustomerService;
import com.test.store.service.ProductService;
import com.test.store.service.RabbitService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
@Log4j2
public class StoreController {

    private final CustomerService customerService;

    private final ProductService productService;

    private final RabbitService rabbitService;

    @Autowired
    public StoreController(CustomerService customerService, ProductService productService, RabbitService rabbitService) {
        this.customerService = customerService;
        this.productService = productService;
        this.rabbitService = rabbitService;
    }

    @GetMapping("/customers/{customerId}")
    public String checkCustomer(@PathVariable(name = "customerId") Long customerId) throws EntityWasNotFoundException {
        log.debug("customerCheck has been called");
        return customerService.retrieveCustomerById(customerId).getName();
    }

    @PostMapping("/orders/add")
    public String addProductToCart(@NotNull @RequestParam(name = "productId") Long productId,
                                   @NotNull @RequestParam(name = "orderId") Long orderId,
                                   @NotNull @RequestParam(name = "amount") int amount)
            throws NotEnoughProductsInWarehouseException, EntityWasNotFoundException {
        log.debug("addProductToCart has been called");
        return productService.addProductToCart(productId, orderId, amount).toString();
    }

    @GetMapping("/ping")
    public String ping() throws IOException, TimeoutException {
        return String.valueOf(rabbitService.send());
    }
}
