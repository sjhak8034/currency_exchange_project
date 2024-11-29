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

    /**
     * 로그인 기능의 서비스 메소드
     * @param dto
     * @return
     */
    public long login(LoginServiceDto dto) {
        //이메일을 통해 유저의 비밀번호를 가져오고 유저가 입력한 값과 비교
        User user = userRepository.findByEmailAndIsDeletedFalse(dto.getEmail());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"email을 확인해주세요");
        }
        // bcrypt 암호화를 해서 passwordEncoder를 사용해서 matches 메소드를 사용해야만 대조 가능
        if(!passwordEncoder.matches(dto.getPassword(),user.getPassword())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"비밀번호가 틀렸습니다");
        }
        return user.getId();
    }

}
