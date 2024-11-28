package com.sparta.user.dto.delete;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class DeleteUserRequestDto {
    @Length(min = 1, max = 20)
    private final String password;

    public DeleteUserRequestDto() {
        password = null;
    }
    public DeleteUserRequestDto(String password) {
        this.password = password;
    }
}
