package com.example.eventwebapplication.services.implemetation;

import com.example.eventwebapplication.exceptions.AuthorityNotFoundException;
import com.example.eventwebapplication.models.Authority;
import com.example.eventwebapplication.repositories.AuthorityRepository;
import com.example.eventwebapplication.services.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void createAuthority(Authority authority) {
        authority.setActive(true);
        authorityRepository.save(authority);
    }

    @Override
    public Authority findAuthorityByName(String name) throws AuthorityNotFoundException {
        Optional<Authority> optionalAuthority = authorityRepository.findByName(name);
        if(optionalAuthority.isEmpty()){
            throw new AuthorityNotFoundException(name);
        }
        return optionalAuthority.get();
    }
    @Override
    public List<Authority> findAuthorities() {
        return null;
    }
}
