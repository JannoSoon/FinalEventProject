package com.example.eventwebapplication.exceptions;

public class AuthorityNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    public AuthorityNotFoundException(String name){
        super(String.format("Authority not found for name: %s", name));
    }
}

