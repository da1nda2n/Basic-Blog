package com.example.blog.user.repository;

import com.example.blog.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long>
{
    Optional<UserEntity> findByLoginId(String loginId);
    boolean existsByLoginId(String loginId);

    Optional<UserEntity> findByUserId(Long userId);
}
