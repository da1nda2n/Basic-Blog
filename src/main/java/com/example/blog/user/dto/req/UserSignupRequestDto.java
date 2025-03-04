package com.example.blog.user.dto.req;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserSignupRequestDto {

    @NotBlank(message = "ID가 입력되지 않았습니다.")
    private String loginId;

    @NotBlank(message = "패스워드가 입력되지 않았습니다.")
    private String password;

    private String name;

}
