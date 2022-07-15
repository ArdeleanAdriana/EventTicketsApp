package com.example.demo.exception;

public class OrganizerNotFoundException extends RuntimeException {
    public OrganizerNotFoundException(String uid) {
        super("Could not find organizer with email: " + uid);
    }
}
