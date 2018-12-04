package com.test.store.exception;

public class NotEnoughProductsInWarehouseException extends Exception {

    public NotEnoughProductsInWarehouseException(String message) {
        super(message);
    }
}
