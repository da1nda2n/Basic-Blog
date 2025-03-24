package com.example.blog.user.entity;

import com.example.blog.post.entity.PostEntity;
import com.example.blog.user.dto.req.UserUpdateRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
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

    @Column
    private String location;

    @Column
    private String birth;

    @Column
    private String phone;

    @Column
    private String introduction;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostEntity> posts;

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

    //현재 인스턴스의 필드를 직접 수정(반환x)
    public void update(UserUpdateRequestDto dto) {
        if(dto.getName() != null) {
            this.name = dto.getName();
        }
        if(dto.getLocation() != null) {
            this.location = dto.getLocation();
        }
        if(dto.getBirth() != null) {
            this.birth = dto.getBirth();
        }
        if(dto.getPhone() != null) {
            this.phone = dto.getPhone();
        }
        if(dto.getIntroduction() != null) {
            this.introduction = dto.getIntroduction();
        }
    }
}
