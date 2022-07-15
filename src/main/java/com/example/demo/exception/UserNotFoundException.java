package com.example.demo.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String uid) {
        super("Could not find user with Uid: " + uid);
    }
}
