package com.example.blog.user.dto.res;

import com.example.blog.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserUpdateResponseDto {
    private final Long userId;
    private final String name;
    private final String location;
    private final String birth;
    private final String phone;
    private final String introduction;

    @Builder
    public UserUpdateResponseDto(Long userId, String name, String location, String birth, String phone, String introduction) {
        this.userId = userId;
        this.name = name;
        this.location = location;
        this.birth = birth;
        this.phone = phone;
        this.introduction = introduction;
    }

    public static UserUpdateResponseDto from(UserEntity user) {
        return UserUpdateResponseDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .location(user.getLocation())
                .birth(user.getBirth())
                .phone(user.getPhone())
                .introduction(user.getIntroduction())
                .build();
    }
}
