package com.example.blog.user.service;

import com.example.blog.user.dto.req.*;
import com.example.blog.user.dto.res.UserGetResponseDto;
import com.example.blog.user.entity.UserEntity;
import com.example.blog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원가입
    public UserEntity signup(UserSignupRequestDto userSignupRequestDto) {
        if(userRepository.existsByLoginId(userSignupRequestDto.getLoginId())){
            throw new RuntimeException("이미 존재하는 ID 입니다.");
        }

        UserEntity user = UserEntity.builder()
                .loginId(userSignupRequestDto.getLoginId())
                .name(userSignupRequestDto.getName())
                .password(userSignupRequestDto.getPassword())
                .build();
        return userRepository.save(user);
    }

    //로그인
    public UserEntity login(UserLoginRequestDto userLoginRequestDto) {
        UserEntity user = userRepository.findByLoginId(userLoginRequestDto.getLoginId())
                .orElseThrow(()-> new RuntimeException("존재하지 않는 ID 입니다."));

        if (!user.getPassword().equals(userLoginRequestDto.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }

    //회원 정보(전체) 수정
    public UserEntity update(Long userId, UserUpdateRequestDto userUpdateRequestDto) {
        UserEntity existingUser = userRepository.findByUserId(userId)
                .orElseThrow(()-> new RuntimeException("존재하지 않는 ID입니다."));
        UserEntity updatedUser = existingUser.update(userUpdateRequestDto);
        return userRepository.save(updatedUser);
    }

    //전체 회원 조회
    public List<UserGetResponseDto> getUserList() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserGetResponseDto> userListResponse = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            userListResponse.add(UserGetResponseDto.from(userEntity));
        }
        return userListResponse;
    }
    //개별 회원 조회
    public UserEntity getUser(Long userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 ID입니다."));
    }

}
