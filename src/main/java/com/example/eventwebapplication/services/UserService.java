package com.example.eventwebapplication.services;

import com.example.eventwebapplication.exceptions.InvalidLoginException;
import com.example.eventwebapplication.models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    void createUser(User user);


    User findUserByUserName(String userName) throws InvalidLoginException;


    User findUserByUserNameAndPassword(String userName, String password) throws InvalidLoginException;


    List<User> findAllUsers();
}
