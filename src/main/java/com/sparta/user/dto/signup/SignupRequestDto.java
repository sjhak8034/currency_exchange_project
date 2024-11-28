package com.sparta.user.dto.signup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class SignupRequestDto {
    @Email
    @NotBlank(message = "이메일은 필수값 입니다")
    private final String email;

    @Length(min = 1, max = 5)
    @NotBlank(message = "이름은 필수값 입니다")
    private final String userName;

    @Length(min = 8, max = 20)
    @NotBlank(message = "비밀번호는 필수값 입니다")
    private final String password;

    private SignupRequestDto(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
    }
}
