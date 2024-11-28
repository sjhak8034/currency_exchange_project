package com.sparta.user.dto.delete;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class DeleteUserServiceDto {
    private final Long id;
    private final String password;
}
