package com.example.blog.user.dto.res;

import com.example.blog.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserGetResponseDto {
    private Long userId;
    private String name;
    private String location;
    private String birth;
    private String phone;

    @Builder
    public UserGetResponseDto(Long userId, String name, String location, String birth, String phone) {
        this.userId = userId;
        this.name = name;
        this.location = location;
        this.birth = birth;
        this.phone = phone;
    }

    public static UserGetResponseDto from(UserEntity user) {
        return UserGetResponseDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .location(user.getLocation())
                .birth(user.getBirth())
                .phone(user.getPhone())
                .build();
    }

}
