package com.example.eventwebapplication.repositories;

import com.example.eventwebapplication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
        Optional<User> findByUserName(String userName);

        Optional<User> findByUserNameAndPassword(String userName, String password);
}
