package com.example.blog.post.dto.res;

import com.example.blog.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostGetResponseDto {
    private Long postId;
    private LocalDateTime postTime;
    private String title;
    private String content;
    private boolean isMain;

    @Builder
    public PostGetResponseDto(Long postId, LocalDateTime postTime, String title, String content, boolean isMain) {
        this.postId = postId;
        this.postTime = postTime;
        this.title = title;
        this.content = content;
        this.isMain = isMain;
    }

    public static PostGetResponseDto from(PostEntity post) {
        return PostGetResponseDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .isMain(post.isMain())
                .build();
    }
}
