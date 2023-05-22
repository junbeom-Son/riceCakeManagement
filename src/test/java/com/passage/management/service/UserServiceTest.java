package com.passage.management.service;

import static org.junit.jupiter.api.Assertions.*;

import com.passage.management.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @Test
    void createTokenTest() {

        //Header 부분 설정
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        //payload 부분 설정
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("KEY", "HelloWorld");
        payloads.put("NickName", "Glenn");
        payloads.put("Age", 30);
        payloads.put("TempAuth", "Y");

        Long expiredTime = 1000 * 60L * 60L * 1L; // 토큰 유효시간 1시간

        Date date = new Date();
        date.setTime(date.getTime() + expiredTime); // 토큰 만료시간

        String secretKey = "secretKeysecretKeysecretKeysecretKeysecretKey";
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String jwt = Jwts.builder()
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims 설정
                .setSubject("Test") // 토큰 용도
                .setExpiration(date) // 토큰 만료 시간 설정
                .signWith(key, SignatureAlgorithm.HS256)
                .compact(); // 토큰 생성

        System.out.println("jwt: " + jwt);
    }
}