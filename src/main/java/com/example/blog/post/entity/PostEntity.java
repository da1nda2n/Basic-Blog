package com.example.blog.post.entity;

import com.example.blog.BaseTimeEntity;
import com.example.blog.post.dto.req.PostUpdateRequestDto;
import com.example.blog.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "posts")
public class PostEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition ="TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean featured;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserEntity user;

    @Builder
    private PostEntity(Long postId, String title, String content, boolean featured, UserEntity user) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.featured = featured;
        this.user = user;
    }

    public PostEntity update(PostUpdateRequestDto dto) {
        return new PostEntity(
                this.postId,
                dto.getTitle() != null ? dto.getTitle() : this.title,
                dto.getContent() != null ? dto.getContent() : this.content,
                dto.isFeatured(),
                this.user
        );
    }
}
