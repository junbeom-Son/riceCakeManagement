package com.passage.management.service;

import com.passage.management.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    public User registerMember(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
}
