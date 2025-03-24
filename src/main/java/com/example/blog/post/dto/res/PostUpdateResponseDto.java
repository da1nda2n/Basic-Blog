package com.example.blog.post.dto.res;

import com.example.blog.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUpdateResponseDto {
    private final Long postId;
    private final boolean featured;

    @Builder
    public PostUpdateResponseDto(Long postId, boolean featured) {
        this.postId = postId;
        this.featured = featured;
    }

    public static PostUpdateResponseDto from(PostEntity post) {
        return PostUpdateResponseDto.builder()
                .postId(post.getPostId())
                .featured(post.isFeatured())
                .build();
    }
}
