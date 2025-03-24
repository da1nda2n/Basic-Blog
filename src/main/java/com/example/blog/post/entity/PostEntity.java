package com.example.blog.post.entity;

import com.example.blog.BaseTimeEntity;
import com.example.blog.post.dto.req.PostUpdateRequestDto;
import com.example.blog.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "posts")
public class PostEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)
    private LocalDateTime postTime;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean featured;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserEntity user;

    @Builder
    private PostEntity(Long postId, LocalDateTime postTime, String title, String content, boolean featured, UserEntity user) {
        this.postId = postId;
        this.postTime = postTime;
        this.title = title;
        this.content = content;
        this.featured = featured;
        this.user = user;
    }

    public PostEntity update(PostUpdateRequestDto dto) {
        return new PostEntity(
                this.postId,
                this.postTime,
                dto.getTitle() != null ? dto.getTitle() : this.title,
                dto.getContent() != null ? dto.getContent() : this.content,
                dto.isFeatured(),
                this.user
        );
    }
}
