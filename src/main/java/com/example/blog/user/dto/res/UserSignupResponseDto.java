package com.example.blog.user.dto.res;

import com.example.blog.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignupResponseDto {
    private String loginId;
    private Long userId;
    private String name;

    @Builder
    public UserSignupResponseDto(String loginId, Long userId, String name) {
        this.loginId = loginId;
        this.userId = userId;
        this.name = name;
    }

    public static UserSignupResponseDto from(UserEntity user) {
        return UserSignupResponseDto.builder()
                .userId(user.getUserId())
                .loginId(user.getLoginId())
                .name(user.getName())
                .build();
    }
}
