package com.passage.management.service;

import com.passage.management.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;

    public Member registerMember(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return member;
    }
}
