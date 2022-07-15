package com.example.demo.exception;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String uid) {
        super("Could not find event with Uid: " + uid);
    }
}