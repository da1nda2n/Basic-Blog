package com.example.blog.post.dto.res;

import com.example.blog.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUpdateResponseDto {
    private final String title;
    private final String content;
    private final boolean featured;

    @Builder
    public PostUpdateResponseDto(String title, String content, boolean featured) {
        this.title = title;
        this.content = content;
        this.featured = featured;
    }


    public static PostUpdateResponseDto from(PostEntity post) {
        return PostUpdateResponseDto.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .featured(post.isFeatured())
                .build();
    }
}
