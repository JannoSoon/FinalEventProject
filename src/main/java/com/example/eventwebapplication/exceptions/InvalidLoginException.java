package com.example.eventwebapplication.exceptions;

public class InvalidLoginException extends Exception{
    public InvalidLoginException(String username) {
        super("Invalid login credentials for username: " + username);
    }
}