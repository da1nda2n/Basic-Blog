package com.example.blog.post.controller;

import com.example.blog.post.dto.req.PostCreateRequestDto;
import com.example.blog.post.dto.req.PostUpdateRequestDto;
import com.example.blog.post.dto.res.PostCreateResponseDto;
import com.example.blog.post.dto.res.PostDeleteResponseDto;
import com.example.blog.post.dto.res.PostGetResponseDto;
import com.example.blog.post.dto.res.PostUpdateResponseDto;
import com.example.blog.post.entity.PostEntity;
import com.example.blog.post.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //게시글 작성
    @PostMapping
    public ResponseEntity<PostCreateResponseDto> create(@Valid @RequestBody PostCreateRequestDto createRequest) {
        PostEntity postEntity = postService.create(createRequest);
        PostCreateResponseDto responseDto = PostCreateResponseDto.from(postEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

    }

    //개별 게시글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostGetResponseDto> getPost(@PathVariable Long postId) {
        PostEntity postEntity = postService.getPost(postId);
        PostGetResponseDto responseDto = PostGetResponseDto.from(postEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //게시글 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<List<PostGetResponseDto>> getPostList() {
        List<PostGetResponseDto> responseList = postService.getPostList();
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    //게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<PostUpdateResponseDto> update(@PathVariable Long postId, @Valid @RequestBody PostUpdateRequestDto updateRequest) {
        PostEntity postEntity = postService.update(postId, updateRequest);
        PostUpdateResponseDto response = PostUpdateResponseDto.from(postEntity);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<PostDeleteResponseDto> delete(@PathVariable Long postId) {
        PostEntity deletedPost = postService.delete(postId);
        PostDeleteResponseDto responseDto = PostDeleteResponseDto.from(deletedPost);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
