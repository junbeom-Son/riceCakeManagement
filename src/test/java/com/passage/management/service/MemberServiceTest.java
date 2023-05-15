package com.passage.management.service;

import static org.junit.jupiter.api.Assertions.*;

import com.passage.management.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberService memberService;

    @Test
    void passwordEncodingTest() {
        String rawPassword = "13579";
        Member member = new Member();
        member.setPassword(rawPassword);
        Member encodedMember = memberService.registerMember(member);
        assertTrue(passwordEncoder.matches(rawPassword, encodedMember.getPassword()));
    }
}