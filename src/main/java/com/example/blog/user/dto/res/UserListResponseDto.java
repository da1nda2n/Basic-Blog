package com.example.blog.user.dto.res;

import com.example.blog.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserListResponseDto {
    private String name;
    private String location;
    private String birth;
    private String phone;

    @Builder
    public UserListResponseDto(String name, String location, String birth, String phone) {
        this.name = name;
        this.location = location;
        this.birth = birth;
        this.phone = phone;
    }

    public static UserListResponseDto from(UserEntity user) {
        return UserListResponseDto.builder()
                .name(user.getName())
                .location(user.getLocation())
                .birth(user.getBirth())
                .phone(user.getPhone())
                .build();
    }

}
