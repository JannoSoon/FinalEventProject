package com.example.eventwebapplication.services.implemetation;

import com.example.eventwebapplication.exceptions.InvalidLoginException;
import com.example.eventwebapplication.models.Login;
import com.example.eventwebapplication.services.LoginService;
import com.example.eventwebapplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserService userService;

    @Override
    public boolean validateLogin(Login login) {
        try {
            userService.findUserByUserNameAndPassword(login.getUserName(), login.getPassword());
            return true;
        } catch (InvalidLoginException ive) {}
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
