package com.example.blog.post.entity;

import com.example.blog.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
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

    @Builder
    public PostEntity(Long postId, LocalDateTime postTime, String title, String content, boolean featured) {
        this.postId = postId;
        this.postTime = postTime;
        this.title = title;
        this.content = content;
        this.featured = featured;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userId;
}
