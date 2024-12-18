package com.sparta.auth.controller;

import com.sparta.auth.dto.LoginRequestDto;
import com.sparta.auth.dto.LoginServiceDto;
import com.sparta.auth.service.AuthService;
import com.sparta.user.dto.signup.SignUpServiceDto;
import com.sparta.user.dto.signup.SignupRequestDto;
import com.sparta.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("currency/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    /**
     * 로그인 기능을 하는 엔드포인트
     * @param body 로그인에 필요한 정보를 담은 body
     * @param request 유저의 로그인 유무 확인
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @Valid LoginRequestDto body, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        // 로그인 확인
        if (session.getAttribute("userId") != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "로그아웃하고 로그인해주세요");
        }

        long id = authService.login(new LoginServiceDto(body.getEmail(), body.getPassword()));
        // 로그인이 끝나면 session에 유저 정보 기록
        session.setAttribute("userId", id);

        return ResponseEntity.ok().build();
    }

    /**
     * 로그아웃 기능을 하는 엔드포인트
     * @param request 로그인 유무 확인
     * @return
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        // 세션을 비활성화 함
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok().build();
    }
    /**
     * 회원 가입 기능의 엔드포인트
     * @param body 유저의 회원가입 정보
     * @param request 유저의 로그인 정보
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@RequestBody @Valid SignupRequestDto body, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        // 이미 로그인 되어있을경우 로그아웃 요청
        if(session != null) {
            if (session.getAttribute("userId") != null) {
                System.out.println(session.getAttribute("userId"));
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그아웃 해주세요");
            }
        }
        userService.save(new SignUpServiceDto(body.getEmail(),body.getPassword(),body.getUserName()));
        return ResponseEntity.ok().build();
    }

}
