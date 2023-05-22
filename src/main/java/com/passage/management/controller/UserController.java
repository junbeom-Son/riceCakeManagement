package com.passage.management.controller;

import com.passage.management.domain.Role;
import com.passage.management.domain.User;
import com.passage.management.dto.LoginDTO;
import com.passage.management.jwt.JwtTokenProvider;
import com.passage.management.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        User user = userService.login(loginDTO);
        Role role = user.getRole();

        String token = jwtTokenProvider.createToken(user.getUserId(), user.getRole());
        response.setHeader("token", token);
        return token;
    }

    @PostMapping("/join")
    @ResponseBody
    public User join(@RequestBody User user) {
        return userService.registerMember(user);
    }
}
