package com.example.blog.post.dto.res;

import com.example.blog.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreateResponseDto {
    private final Long postId;
    private final Long userId;
    private final boolean featured;

    @Builder
    public PostCreateResponseDto(Long postId, Long userId, boolean featured) {
        this.postId = postId;
        this.userId = userId;
        this.featured = featured;
    }

    public static PostCreateResponseDto from(PostEntity post) {
        Long userId = (post.getUser() != null) ? post.getUser().getUserId() : null;
        return PostCreateResponseDto.builder()
                .postId(post.getPostId())
                .userId(userId)
                .featured(post.isFeatured())
                .build();
    }
}
