package com.example.blog.post.entity;

import com.example.blog.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
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
    private boolean isMain;

    @Builder
    public PostEntity(Long postId, LocalDateTime postTime, String title, String content, boolean isMain) {
        this.postId = postId;
        this.postTime = postTime;
        this.title = title;
        this.content = content;
        this.isMain = isMain;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userId;
}
