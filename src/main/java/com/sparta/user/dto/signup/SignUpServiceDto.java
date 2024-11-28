package com.sparta.user.dto.signup;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpServiceDto {
    private final String email;
    private final String password;
    private final String userName;
}
