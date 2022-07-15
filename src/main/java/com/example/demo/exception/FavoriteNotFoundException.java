package com.example.demo.exception;

public class FavoriteNotFoundException extends RuntimeException {
    public FavoriteNotFoundException(String uid) {
        super("Could not find favorite with Uid: " + uid);
    }
}

