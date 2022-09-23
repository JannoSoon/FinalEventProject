package com.example.eventwebapplication.services;

import com.example.eventwebapplication.exceptions.AuthorityNotFoundException;
import com.example.eventwebapplication.models.Authority;

import java.util.List;

public interface AuthorityService {  void createAuthority(Authority authority);

    Authority findAuthorityByName(String name) throws AuthorityNotFoundException;

    List<Authority> findAuthorities();
}

