package com.passage.management.controller;

import com.passage.management.jwt.JwtTokenProvider;
import com.passage.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    
}
