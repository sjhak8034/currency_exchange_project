package com.sparta.user.dto.update;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateUserServiceDto {
    private final Long id;
    private final String username;
    private final String beforePassword;
    private final String afterPassword;
}
