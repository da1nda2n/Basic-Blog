package com.example.blog.post.service;

import com.example.blog.post.dto.req.PostCreateRequestDto;
import com.example.blog.post.dto.req.PostUpdateRequestDto;
import com.example.blog.post.dto.res.PostGetResponseDto;
import com.example.blog.post.entity.PostEntity;
import com.example.blog.post.repository.PostRepository;
import com.example.blog.user.entity.UserEntity;
import com.example.blog.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    public final PostRepository postRepository;
    public final UserRepository userRepository;

    //게시글 작성
    public PostEntity create(Long requestUserId, PostCreateRequestDto postCreateRequestDto) {
        UserEntity user = userRepository.findById(requestUserId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        PostEntity post = PostEntity.builder()
                .title(postCreateRequestDto.getTitle())
                .content(postCreateRequestDto.getContent())
                .featured(postCreateRequestDto.isFeatured())
                .user(user)
                .build();
        return postRepository.save(post);
    }

    //개별 게시글 조회(열람)
    @Transactional(readOnly = true)
    public PostEntity getPost(Long postId) {
        return postRepository.findByPostId(postId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
    }

    //게시글 리스트 조회
    @Transactional(readOnly = true)
    public List<PostGetResponseDto> getPostList() {
        List<PostEntity> postEntities = postRepository.findAll();
        List<PostGetResponseDto> responseList = new ArrayList<>();
        for (PostEntity postEntity : postEntities) {
            String name = postEntity.getUser() != null ? postEntity.getUser().getName() : null;
            responseList.add(PostGetResponseDto.from(postEntity, name));
        }
        return responseList;
    }

    //게시글 수정
    @Transactional
    public PostEntity update(Long postId, Long requestUserId, PostUpdateRequestDto postUpdateRequestDto) {
        PostEntity existingPost = postRepository.findByPostId(postId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));

        UserEntity user = userRepository.findById(requestUserId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        if (existingPost.getUser() == null || !existingPost.getUser().getUserId().equals(requestUserId)) {
            throw new RuntimeException("수정 권한이 없습니다.");
        }
        PostEntity updatedPost = existingPost.update(postUpdateRequestDto);
        return postRepository.save(updatedPost);
    }

    //게시글 삭제
    public PostEntity delete(Long postId, Long requestUserId) {
        PostEntity post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));

        UserEntity user = userRepository.findById(requestUserId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        if (post.getUser() == null || !post.getUser().getUserId().equals(requestUserId)) {
            throw new RuntimeException("삭제 권한이 없습니다.");
        }
        postRepository.delete(post);
        return post;
    }
}
