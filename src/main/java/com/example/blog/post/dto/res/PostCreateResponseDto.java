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
    private Long userId;
    private String name;
    private String title;
    private String content;
    private boolean featured;
    private LocalDateTime postTime;

    @Builder
    public PostCreateResponseDto(Long postId, Long userId, String name, String title, String content, boolean featured, LocalDateTime postTime) {
        this.postId = postId;
        this.userId = userId;
        this.name = name;
        this.title = title;
        this.content = content;
        this.featured = featured;
        this.postTime = postTime;
    }


    public static PostCreateResponseDto from(PostEntity post, String name) {
        Long userId = (post.getUserId() != null) ? post.getUserId().getUserId() : null;
        return PostCreateResponseDto.builder()
                .postId(post.getPostId())
                .userId(userId)
                .name(name)
                .title(post.getTitle())
                .content(post.getContent())
                .featured(post.isFeatured())
                .postTime(post.getPostTime())
                .build();
    }
}
