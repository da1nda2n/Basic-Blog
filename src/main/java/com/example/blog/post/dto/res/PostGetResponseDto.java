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
    private Long userId;
    private String name;
    private LocalDateTime postTime;
    private String title;
    private String content;
    private boolean featured;

    @Builder
    public PostGetResponseDto(Long postId, Long userId, String name, LocalDateTime postTime, String title, String content, boolean featured) {
        this.postId = postId;
        this.userId = userId;
        this.name = name;
        this.postTime = postTime;
        this.title = title;
        this.content = content;
        this.featured = featured;
    }

    public static PostGetResponseDto from(PostEntity post, String name) {
        Long userId = (post.getUserId() != null) ? post.getUserId().getUserId() : null;
        return PostGetResponseDto.builder()
                .postId(post.getPostId())
                .userId(userId)
                .name(name)
                .postTime(post.getPostTime())
                .title(post.getTitle())
                .content(post.getContent())
                .featured(post.isFeatured())
                .build();
    }
}
