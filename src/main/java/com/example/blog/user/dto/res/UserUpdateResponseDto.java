package com.example.blog.user.dto.res;

import com.example.blog.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateResponseDto {
    private String name;
    private String location;
    private String birth;
    private String phone;
    private String introduction;

    @Builder
    public UserUpdateResponseDto(String name, String location, String birth, String phone, String introduction) {
        this.name = name;
        this.location = location;
        this.birth = birth;
        this.phone = phone;
        this.introduction = introduction;
    }

    public static UserUpdateResponseDto from(UserEntity user) {
        return UserUpdateResponseDto.builder()
                .name(user.getName())
                .location(user.getLocation())
                .birth(user.getBirth())
                .phone(user.getPhone())
                .introduction(user.getIntroduction())
                .build();
    }
}
