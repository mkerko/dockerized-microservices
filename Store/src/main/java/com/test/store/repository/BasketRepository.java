package com.test.store.repository;

import com.test.store.domain.Basket;
import com.test.store.domain.Order;
import com.test.store.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends CrudRepository<Basket, Long> {
    Basket getBasketByOrderAndProduct(Order order, Product product);
}
