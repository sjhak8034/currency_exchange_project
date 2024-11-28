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

@RestController
@RequestMapping("/currency/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid SignupRequestDto body, HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session.getAttribute("userId") != null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그아웃 해주세요");
        }
        userService.save(new SignUpServiceDto(body.getEmail(),body.getPassword(),body.getUserName()));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/profile/{userId}")
    public ResponseEntity<Void> updateUser(@RequestBody @Valid UpdateUserRequestDto body, @PathVariable(name = "userId") Long userId ,HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        userService.update(new UpdateUserServiceDto((long)session.getAttribute("userId"), body.getUserName(),body.getBeforePassword(), body.getAfterPassword()));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<Void> deleteUser(@RequestBody @Valid DeleteUserRequestDto body, @PathVariable(name = "userId") Long userId,HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        userService.delete(new DeleteUserServiceDto((long)session.getAttribute("userId"),body.getPassword()));
        session.invalidate();
        return ResponseEntity.ok().build();
    }
}
