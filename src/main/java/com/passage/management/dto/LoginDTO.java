package com.passage.management.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoginDTO {
    private String loginId;
    private String password;
}
