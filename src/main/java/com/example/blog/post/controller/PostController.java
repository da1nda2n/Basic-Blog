package com.example.blog.post.controller;

import com.example.blog.post.dto.req.PostCreateRequestDto;
import com.example.blog.post.dto.req.PostUpdateRequestDto;
import com.example.blog.post.dto.res.PostCreateResponseDto;
import com.example.blog.post.dto.res.PostDeleteResponseDto;
import com.example.blog.post.dto.res.PostGetResponseDto;
import com.example.blog.post.dto.res.PostUpdateResponseDto;
import com.example.blog.post.entity.PostEntity;
import com.example.blog.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "게시글 작성")
    @PostMapping
    public ResponseEntity<PostCreateResponseDto> create(@RequestParam Long userId,
                                                        @Valid @RequestBody PostCreateRequestDto createRequest) {
        PostEntity postEntity = postService.create(userId, createRequest);
        String name = postEntity.getUserId() != null ? postEntity.getUserId().getName() : null;
        PostCreateResponseDto responseDto = PostCreateResponseDto.from(postEntity, name);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(summary = "개별 게시글 조회")
    @GetMapping("/{postId}")
    public ResponseEntity<PostGetResponseDto> getPost(@PathVariable Long postId) {
        PostEntity postEntity = postService.getPost(postId);
        String name = postEntity.getUserId() != null ? postEntity.getUserId().getName() : null;
        PostGetResponseDto responseDto = PostGetResponseDto.from(postEntity, name);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Operation(summary = "게시글 리스트 조회")
    @GetMapping("/list")
    public ResponseEntity<List<PostGetResponseDto>> getPostList() {
        List<PostGetResponseDto> responseList = postService.getPostList();
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @Operation(summary = "게시글 수정")
    @PutMapping("/{postId}")
    public ResponseEntity<PostUpdateResponseDto> update(@PathVariable Long postId,
                                                        @RequestParam Long userId,
                                                        @Valid @RequestBody PostUpdateRequestDto updateRequest) {
        PostEntity postEntity = postService.update(postId, userId, updateRequest);
        PostUpdateResponseDto response = PostUpdateResponseDto.from(postEntity);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{postId}")
    public ResponseEntity<PostDeleteResponseDto> delete(@PathVariable Long postId,
                                                        @RequestParam Long userId) {
        PostEntity deletedPost = postService.delete(postId, userId);
        PostDeleteResponseDto responseDto = PostDeleteResponseDto.from(deletedPost);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
