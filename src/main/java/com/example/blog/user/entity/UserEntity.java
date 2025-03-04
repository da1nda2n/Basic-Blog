package com.example.blog.user.entity;

import com.example.blog.post.entity.PostEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String location;

    @Column(nullable = true)
    private String birth;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    private String introduction;

    @Builder
    public UserEntity(String loginId, Long userId, String password, String name, String location, String birth, String phone, String introduction) {
        this.userId = userId;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.location = location;
        this.birth = birth;
        this.phone = phone;
        this.introduction = introduction;
    }

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostEntity> post;


}
