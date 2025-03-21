package com.example.blog.post.dto.res;

import com.example.blog.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateResponseDto {
    private Long userId;
    private String name;
    private String title;
    private String content;
    private boolean featured;

    @Builder
    public PostUpdateResponseDto(Long userId, String name, String title, String content, boolean featured) {
        this.userId = userId;
        this.name = name;
        this.title = title;
        this.content = content;
        this.featured = featured;
    }


    public static PostUpdateResponseDto from(PostEntity post, String name) {
        Long userId = (post.getUserId() != null) ? post.getUserId().getUserId() : null;
        return PostUpdateResponseDto.builder()
                .userId(userId)
                .name(name)
                .title(post.getTitle())
                .content(post.getContent())
                .featured(post.isFeatured())
                .build();
    }
}
