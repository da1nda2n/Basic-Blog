package com.example.blog.post.dto.res;

import com.example.blog.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreateResponseDto {
    private final Long postId;
    private final boolean featured;

    @Builder
    public PostCreateResponseDto(Long postId, boolean featured) {
        this.postId = postId;
        this.featured = featured;
    }

    public static PostCreateResponseDto from(PostEntity post) {
        return PostCreateResponseDto.builder()
                .postId(post.getPostId())
                .featured(post.isFeatured())
                .build();
    }
}
