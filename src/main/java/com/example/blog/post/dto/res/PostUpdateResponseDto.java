package com.example.blog.post.dto.res;

import com.example.blog.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateResponseDto {
    private String title;
    private String content;
    private boolean isMain;

    @Builder
    public PostUpdateResponseDto(String title, String content, boolean isMain) {
        this.title = title;
        this.content = content;
        this.isMain = isMain;
    }


    public static PostUpdateResponseDto from(PostEntity post) {
        return PostUpdateResponseDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .isMain(post.isMain())
                .build();
    }
}
