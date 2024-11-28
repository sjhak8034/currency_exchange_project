package com.sparta.user.dto.update;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UpdateUserRequestDto {

    @Length(min = 1, max = 5)
    @NotBlank(message = "이름은 필수값 입니다")
    private final String userName;

    @Length(min = 8, max = 20)
    @NotBlank(message = "비밀번호는 필수값 입니다")
    private final String beforePassword;

    @Length(min = 8, max = 20)
    @NotBlank(message = "비밀번호는 필수값 입니다")
    private final String afterPassword;

    private UpdateUserRequestDto(String userName, String beforePassword, String afterPassword) {
        this.userName = userName;
        this.beforePassword = beforePassword;
        this.afterPassword = afterPassword;
    }
}
