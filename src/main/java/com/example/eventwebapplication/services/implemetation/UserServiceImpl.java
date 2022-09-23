package com.example.eventwebapplication.services.implemetation;

import com.example.eventwebapplication.exceptions.InvalidLoginException;
import com.example.eventwebapplication.models.User;
import com.example.eventwebapplication.repositories.UserRepository;
import com.example.eventwebapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

   @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public void createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public User findUserByUserName(String userName) throws InvalidLoginException {
        Optional<User> optionalUser = userRepository.findByUserName(userName);

        if(optionalUser.isEmpty()) {
            throw new InvalidLoginException(userName);
        }

        return optionalUser.get();
    }

    @Override
    public User findUserByUserNameAndPassword(String userName, String password) throws InvalidLoginException {
        Optional<User> optionalUser = userRepository.findByUserNameAndPassword(userName, password);

        if(optionalUser.isEmpty()) {
            throw new InvalidLoginException(userName);
        }

        return optionalUser.get();
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
