package com.example.blog.post.dto.res;

import com.example.blog.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreateResponseDto {
    private final Long postId;
    private final Long userId;
    private final String name;
    private final String title;
    private final String content;
    private final boolean featured;

    @Builder
    public PostCreateResponseDto(Long postId, Long userId, String name, String title, String content, boolean featured) {
        this.postId = postId;
        this.userId = userId;
        this.name = name;
        this.title = title;
        this.content = content;
        this.featured = featured;
    }


    public static PostCreateResponseDto from(PostEntity post, String name) {
        Long userId = (post.getUser() != null) ? post.getUser().getUserId() : null;
        return PostCreateResponseDto.builder()
                .postId(post.getPostId())
                .userId(userId)
                .name(name)
                .title(post.getTitle())
                .content(post.getContent())
                .featured(post.isFeatured())
                .build();
    }
}
