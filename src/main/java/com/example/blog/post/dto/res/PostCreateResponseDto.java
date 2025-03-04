package com.example.blog.post.dto.res;

import com.example.blog.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostCreateResponseDto {
    private String title;
    private String content;
    private boolean isMain;

    @Builder
    public PostCreateResponseDto(String title, String content, boolean isMain) {
        this.title = title;
        this.content = content;
        this.isMain = isMain;
    }


    public static PostCreateResponseDto from(PostEntity post) {
        return PostCreateResponseDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .isMain(post.isMain())
                .build();
    }
}
