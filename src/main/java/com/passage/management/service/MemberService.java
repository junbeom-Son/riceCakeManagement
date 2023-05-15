package com.passage.management.service;

import main.java.com.passage.management.domain.Member;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private PasswordEncoder passwordEncoder;

    public Member registerMember(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return member;
    }
}
