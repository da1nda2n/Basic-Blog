package com.example.blog.user.service;

import com.example.blog.user.dto.req.UserLoginRequestDto;
import com.example.blog.user.dto.req.UserSignupRequestDto;
import com.example.blog.user.dto.req.UserUpdateRequestDto;
import com.example.blog.user.dto.res.UserListResponseDto;
import com.example.blog.user.entity.UserEntity;
import com.example.blog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    //회원가입
    public UserEntity signup(UserSignupRequestDto userSignupRequestDto) {
        if(userRepository.existsByLoginId(userSignupRequestDto.getLoginId())){
            throw new RuntimeException("이미 존재하는 ID 입니다.");
        }
        UserEntity user = new UserEntity();
        user.setLoginId(userSignupRequestDto.getLoginId());
        user.setName(userSignupRequestDto.getName());
        user.setPassword(userSignupRequestDto.getPassword()); //보안 위험
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
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("존재하지 않는 사용자입니다."));

        user.setName(userUpdateRequestDto.getName());
        user.setLocation(userUpdateRequestDto.getLocation());
        user.setBirth(userUpdateRequestDto.getBirth());
        user.setPhone(userUpdateRequestDto.getPhone());
        user.setIntroduction(userUpdateRequestDto.getIntroduction());
        return userRepository.save(user);
    }

    //전체 회원 조회 -> 별도의 dto 필요?
    public List<UserListResponseDto> getUserList() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserListResponseDto> userListResponseDtoList = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            userListResponseDtoList.add(UserListResponseDto.from(userEntity));
        }
        return userListResponseDtoList;
    }

}
