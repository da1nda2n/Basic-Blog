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
    private LocalDateTime postTime;
    private String title;
    private String content;
    private boolean featured;

    @Builder
    public PostGetResponseDto(Long postId, LocalDateTime postTime, String title, String content, boolean featured) {
        this.postId = postId;
        this.postTime = postTime;
        this.title = title;
        this.content = content;
        this.featured = featured;
    }

    public static PostGetResponseDto from(PostEntity post) {
        return PostGetResponseDto.builder()
                .postId(post.getPostId())
                .postTime(post.getPostTime())
                .title(post.getTitle())
                .content(post.getContent())
                .featured(post.isFeatured())
                .build();
    }
}
