package com.example.blog.post.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PostUpdateRequestDto {
    @NotBlank(message = "제목이 입력되지 않았습니다.")
    private String title;

    @NotBlank(message = "내용이 입력되지 않았습니다.")
    private String content;

    @NotNull
    private boolean featured;
}
