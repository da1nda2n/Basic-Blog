package com.example.blog.user.service;

import com.example.blog.exception.CustomException;
import com.example.blog.exception.ErrorCode;
import com.example.blog.user.dto.req.*;
import com.example.blog.user.dto.res.UserGetResponseDto;
import com.example.blog.user.entity.UserEntity;
import com.example.blog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원가입
    @Transactional
    public UserEntity signup(UserSignupRequestDto userSignupRequestDto) {
        if(userRepository.existsByLoginId(userSignupRequestDto.getLoginId())){
            throw new CustomException(ErrorCode.DUPLICATE_LOGIN_ID);
        }

        UserEntity user = UserEntity.builder()
                .loginId(userSignupRequestDto.getLoginId())
                .name(userSignupRequestDto.getName())
                .password(userSignupRequestDto.getPassword())
                .build();
        return userRepository.save(user);
    }

    //로그인
    @Transactional
    public UserEntity login(UserLoginRequestDto userLoginRequestDto) {
        UserEntity user = userRepository.findByLoginId(userLoginRequestDto.getLoginId())
                .orElseThrow(()-> new CustomException(ErrorCode.ID_NOT_FOUND));

        if (!user.getPassword().equals(userLoginRequestDto.getPassword())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_LOGIN);
        }
        return user;
    }

    //회원 정보(전체) 수정
    @Transactional
    public UserEntity update(Long userId, UserUpdateRequestDto userUpdateRequestDto) {
        UserEntity existingUser = userRepository.findByUserId(userId)
                .orElseThrow(()-> new CustomException(ErrorCode.ID_NOT_FOUND));
        existingUser.update(userUpdateRequestDto); // 기존 인스턴스의 필드 직접 업데이트
        return userRepository.save(existingUser);
    }

    //전체 회원 조회
    @Transactional(readOnly = true)
    public List<UserGetResponseDto> getUserList() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserGetResponseDto> userListResponse = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            userListResponse.add(UserGetResponseDto.from(userEntity));
        }
        return userListResponse;
    }
    //개별 회원 조회
    @Transactional(readOnly = true)
    public UserEntity getUser(Long userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.ID_NOT_FOUND));
    }

}
