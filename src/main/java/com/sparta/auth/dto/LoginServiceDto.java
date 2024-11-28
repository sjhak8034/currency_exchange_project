package com.sparta.auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginServiceDto {
    private final String email;
    private final String password;
}
