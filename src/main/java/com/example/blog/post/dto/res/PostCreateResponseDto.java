package com.example.blog.post.dto.res;

import com.example.blog.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostCreateResponseDto {
    private Long postId;
    private String title;
    private String content;
    private boolean isMain;
    private LocalDateTime createTime;

    @Builder
    public PostCreateResponseDto(Long postId, String title, String content, boolean isMain, LocalDateTime createdAt) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.isMain = isMain;
        this.createTime = createdAt;
    }


    public static PostCreateResponseDto from(PostEntity post) {
        return PostCreateResponseDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .isMain(post.isMain())
                .createdAt(post.getPostTime())
                .build();
    }
}
