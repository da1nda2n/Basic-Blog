package com.example.blog.post.service;

import com.example.blog.post.dto.req.PostCreateRequestDto;
import com.example.blog.post.dto.req.PostUpdateRequestDto;
import com.example.blog.post.dto.res.PostGetResponseDto;
import com.example.blog.post.entity.PostEntity;
import com.example.blog.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    public final PostRepository postRepository;

    //게시글 작성
    public PostEntity create(PostCreateRequestDto postCreateRequestDto) {
        PostEntity post = new PostEntity();
        post.setTitle(postCreateRequestDto.getTitle());
        post.setContent(postCreateRequestDto.getContent());
        post.setMain(postCreateRequestDto.isMain());
        return postRepository.save(post);
    }

    //개별 게시글 조회(열람)
    public PostEntity getPost(Long postId) {
        return postRepository.findByPostId(postId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
    }

    //게시글 리스트 조회
    public List<PostGetResponseDto> getPostList() {
        List<PostEntity> postEntities = postRepository.findAll();
        List<PostGetResponseDto> postListResponseDtoListShow = new ArrayList<>();
        for (PostEntity postEntity : postEntities) {
            postListResponseDtoListShow.add(PostGetResponseDto.from(postEntity));
        }
        return postListResponseDtoListShow;
    }

    //게시글 수정
    public PostEntity update(Long postId, PostUpdateRequestDto postUpdateRequestDto) {
        PostEntity post = postRepository.findByPostId(postId)
                .orElseThrow(()-> new RuntimeException("존재하지 않는 게시글입니다."));

        post.setTitle(postUpdateRequestDto.getTitle());
        post.setContent(postUpdateRequestDto.getContent());
        post.setMain(postUpdateRequestDto.isMain());
        return postRepository.save(post);
    }

    //게시글 삭제
    public PostEntity delete(Long postId) {
        PostEntity post = postRepository.findByPostId(postId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));
        postRepository.delete(post);
        return post;
    }
}
