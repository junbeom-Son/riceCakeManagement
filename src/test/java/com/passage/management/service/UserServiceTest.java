package com.passage.management.service;

import static org.junit.jupiter.api.Assertions.*;

import com.passage.management.domain.User;
import org.assertj.core.api.Assertions;
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

    @Test
    void registerTest() {
        String loginId = "test1";
        String password = "pass1";
        String address = "해운대";
        String name = "user1";
        String phone = "010-4885-4885";
        User user = new User();
        user.setLoginId(loginId);
        user.setPassword(password);
        user.setAddress(address);
        user.setName(name);
        user.setPhone(phone);
        User savedUser = userService.registerMember(user);
        assertTrue(passwordEncoder.matches(password, savedUser.getPassword()));
    }

    @Test
    void loginTest() {
        String loginId = "test1";
        String password = "pass1";
        User user = userService.login(loginId, password);
        assertNotNull(user);
        assertTrue(passwordEncoder.matches(password, user.getPassword()));
    }
}