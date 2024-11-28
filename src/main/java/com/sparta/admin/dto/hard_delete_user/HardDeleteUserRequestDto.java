package com.sparta.admin.dto.hard_delete_user;

import lombok.Getter;

@Getter
public class HardDeleteUserRequestDto {
    private Long userId;

    private HardDeleteUserRequestDto(Long userId) {
        this.userId = userId;
    }
    private HardDeleteUserRequestDto() {}
}
