package com.example.demo.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String field) {
        super("Could not find customer with: " + field);
    }
}
