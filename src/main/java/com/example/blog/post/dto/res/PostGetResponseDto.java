package com.example.blog.post.dto.res;

import com.example.blog.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostGetResponseDto {
    private final Long postId;
    private final Long userId;
    private final String name;
    private final String title;
    private final String content;
    private final boolean featured;
    private final LocalDate createdAt;

    @Builder
    public PostGetResponseDto(Long postId, Long userId, String name, String title, String content, boolean featured, LocalDate createdAt) {
        this.postId = postId;
        this.userId = userId;
        this.name = name;
        this.title = title;
        this.content = content;
        this.featured = featured;
        this.createdAt = createdAt;
    }

    public static PostGetResponseDto from(PostEntity post, String name) {
        Long userId = (post.getUser() != null) ? post.getUser().getUserId() : null;
        LocalDate postTime = post.getCreatedAt();
        return PostGetResponseDto.builder()
                .postId(post.getPostId())
                .userId(userId)
                .name(name)
                .title(post.getTitle())
                .content(post.getContent())
                .featured(post.isFeatured())
                .createdAt(postTime)
                .build();
    }
}
