package com.sparta.user.controller;

import com.sparta.user.dto.delete.DeleteUserRequestDto;
import com.sparta.user.dto.delete.DeleteUserServiceDto;
import com.sparta.user.dto.signup.SignUpServiceDto;
import com.sparta.user.dto.signup.SignupRequestDto;
import com.sparta.user.dto.update.UpdateUserRequestDto;
import com.sparta.user.dto.update.UpdateUserServiceDto;
import com.sparta.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@RestController
@RequestMapping("/currency/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원 가입 기능의 엔드포인트
     * @param body 유저의 회원가입 정보
     * @param request 유저의 로그인 정보
     * @return
     */
    @PostMapping
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

    /**
     * 유저 회원정보 수정을 기능의 엔드포인트
     * @param body 유저가 수정할정보
     * @param userId 유저 식별자
     * @param request 유저 로그인 정보
     * @return
     */
    @PutMapping("/profile/{userId}")
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UpdateUserRequestDto body, @PathVariable(name = "userId") Long userId ,HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        // 로그인 정보랑 url의 정보가 일치하는지 확인
        if (!Objects.equals((Long) session.getAttribute("userId"), userId)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        userService.update(new UpdateUserServiceDto((long)session.getAttribute("userId"), body.getUserName(),body.getBeforePassword(), body.getAfterPassword()));
        return ResponseEntity.ok().build();
    }

    /**
     * 회원 탈퇴 기능의 엔드포인트 (soft delete)
     * @param body 본인 확인용 유저의 비밀번호를 담은 엔드포인트
     * @param userId 유저 식별자
     * @param request
     * @return
     */
    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@RequestBody @Valid DeleteUserRequestDto body, @PathVariable(name = "userId") Long userId,HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        userService.delete(new DeleteUserServiceDto((long)session.getAttribute("userId"),body.getPassword()));
        session.invalidate();
        return ResponseEntity.ok().build();
    }
}
