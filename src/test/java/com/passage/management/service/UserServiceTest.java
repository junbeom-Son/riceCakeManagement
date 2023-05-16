package com.passage.management.service;

import static org.junit.jupiter.api.Assertions.*;

import com.passage.management.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class UserServiceTest {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    @Test
    void passwordEncodingTest() {
        String rawPassword = "13579";
        User user = new User();
        user.setPassword(rawPassword);
        User encodedUser = userService.registerMember(user);
        assertTrue(passwordEncoder.matches(rawPassword, encodedUser.getPassword()));
    }
}