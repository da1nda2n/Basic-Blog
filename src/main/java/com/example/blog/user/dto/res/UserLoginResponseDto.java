package com.example.blog.user.dto.res;

import com.example.blog.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginResponseDto {
    private Long userId;
    private String name;

    @Builder
    public UserLoginResponseDto(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public static UserLoginResponseDto from(UserEntity user) {
        return UserLoginResponseDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .build();
    }
}
