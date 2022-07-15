package com.example.demo.exception;

public class PasswordIncorrectException extends RuntimeException{
    public PasswordIncorrectException(String username) {
        super("Password incorrect for user: " + username);
    }
}
