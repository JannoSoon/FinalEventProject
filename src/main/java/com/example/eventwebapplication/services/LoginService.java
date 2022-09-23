package com.example.eventwebapplication.services;

import com.example.eventwebapplication.exceptions.InvalidLoginException;
import com.example.eventwebapplication.models.Login;
import com.example.eventwebapplication.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginService extends UserDetails {
    public boolean validateLogin(Login login) throws InvalidLoginException;
}
