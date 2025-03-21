package com.example.blog.post.entity;

import com.example.blog.post.dto.req.PostUpdateRequestDto;
import com.example.blog.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "posts")
public class PostEntity {
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

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userId;

    public PostEntity update(PostUpdateRequestDto dto) {
        return new PostEntity(
                this.postId,
                this.postTime,
                dto.getTitle() != null ? dto.getTitle() : this.title,
                dto.getContent() != null ? dto.getContent() : this.content,
                dto.isFeatured(),
                this.userId
        );
    }

}
