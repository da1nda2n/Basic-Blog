package com.example.blog.user.dto.req;

import lombok.Getter;

@Getter
public class UserUpdateRequestDto {
    private String name;

    private String location;

    private String birth;

    private String phone;

    private String introduction;
}
