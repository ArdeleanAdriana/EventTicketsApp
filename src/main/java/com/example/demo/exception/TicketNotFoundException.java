package com.example.demo.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String uid) {
        super("Could not find event with Uid: " + uid);
    }
}
