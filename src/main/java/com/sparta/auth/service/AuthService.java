package com.sparta.auth.service;

import com.sparta.auth.dto.LoginServiceDto;

import com.sparta.common.config.PasswordEncoder;
import com.sparta.user.entity.User;
import com.sparta.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public long login(LoginServiceDto dto) {
        User user = userRepository.findByEmailAndIsDeletedFalse(dto.getEmail());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"email을 확인해주세요");
        }
        if(!passwordEncoder.matches(dto.getPassword(),user.getPassword())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"비밀번호가 틀렸습니다");
        }
        return user.getId();
    }

}
