package com.sparta.user.service;

import com.sparta.common.config.PasswordEncoder;
import com.sparta.user.dto.delete.DeleteUserServiceDto;
import com.sparta.user.dto.signup.SignUpServiceDto;
import com.sparta.user.dto.update.UpdateUserServiceDto;
import com.sparta.user.entity.User;
import com.sparta.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 유저 등록 메소드
     * @param dto user의 name email paassword 정보가 담긴 dto
     */
    public void save(SignUpServiceDto dto) {

        User user = new User(dto.getUserName(), dto.getEmail(), passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);

    }

    /**
     * 유저 정보 수정 메소드
     * @param dto 변경할 정보 및 확인용 비밀번호가 담긴 dto
     */
    public void update(UpdateUserServiceDto dto) {
        User updatableUser = userRepository.findByIdAndIsDeleted(dto.getId(), false);
        if (updatableUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾지 못했습니다");
        }
        if(!passwordEncoder.matches(dto.getBeforePassword(), updatableUser.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
        }
        updatableUser.update(dto.getUsername(),passwordEncoder.encode(dto.getAfterPassword()));
        userRepository.save(updatableUser);
    }

    /**
     * 유저 soft delete 메소드
     * @param dto 삭제할 id 및 확인용 비밀번호 가 담긴 dto
     */
    public void delete(DeleteUserServiceDto dto) {
        User deletalbeUser = userRepository.findByIdOrElseThrow(dto.getId());
        // 유저 비밀번호가 다를경우 예외처리
        if(!passwordEncoder.matches(dto.getPassword(), deletalbeUser.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
        }
        deletalbeUser.delete();
        userRepository.save(deletalbeUser);
    }

}
