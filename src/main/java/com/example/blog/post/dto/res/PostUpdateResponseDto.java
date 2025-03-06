package com.example.blog.post.dto.res;

import com.example.blog.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateResponseDto {
    private String title;
    private String content;
    private boolean featured;

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
