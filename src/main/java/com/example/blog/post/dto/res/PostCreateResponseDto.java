package com.example.blog.post.dto.res;

import com.example.blog.post.entity.PostEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostCreateResponseDto {
    private Long postId;
    private String title;
    private String content;
    private boolean featured;
    private LocalDateTime postTime;

    @Builder
    public PostCreateResponseDto(Long postId, String title, String content, boolean featured, LocalDateTime postTime) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.featured = featured;
        this.postTime = postTime;
    }


    public static PostCreateResponseDto from(PostEntity post) {
        return PostCreateResponseDto.builder()
                .postId(post.getPostId())
                .title(post.getTitle())
                .content(post.getContent())
                .featured(post.isFeatured())
                .postTime(post.getPostTime())
                .build();
    }
}
