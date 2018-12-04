package com.test.store.service;

import com.test.store.domain.Order;
import com.test.store.exception.EntityWasNotFoundException;
import com.test.store.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    Order retrieveOrderById(long orderId) throws EntityWasNotFoundException {
        log.debug("retrieveOrderById has been called with id = " + orderId);
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        orderOptional.orElseThrow(() -> new EntityWasNotFoundException("Could not find order with id = " + orderId));
        return orderOptional.get();
    }
}
