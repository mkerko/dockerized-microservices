package com.test.store.controller;

import com.test.store.exception.EntityWasNotFoundException;
import com.test.store.exception.NotEnoughProductsInWarehouseException;
import com.test.store.exception.WarehouseServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotEnoughProductsInWarehouseException.class)
    public ResponseEntity<String> NotEnoughProductsInWarehouse(final NotEnoughProductsInWarehouseException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FAILED_DEPENDENCY);
    }

    @ExceptionHandler(EntityWasNotFoundException.class)
    public ResponseEntity<String> notFoundException(final EntityWasNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WarehouseServiceUnavailableException.class)
    public ResponseEntity<String> warehouseIsUnavailable(final WarehouseServiceUnavailableException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> rabbitMQProblem(final IOException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TimeoutException.class)
    public ResponseEntity<String> rabbitMQTimedOut(final TimeoutException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
