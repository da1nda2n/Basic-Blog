package com.example.blog.post.controller;

import com.example.blog.post.dto.req.*;
import com.example.blog.post.dto.res.*;
import com.example.blog.post.entity.PostEntity;
import com.example.blog.post.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Operation(summary = "게시글 작성")
    @PostMapping
    public ResponseEntity<PostCreateResponseDto> create(@RequestParam Long userId,
                                                        @Valid @RequestBody PostCreateRequestDto createRequest) {
        PostEntity postEntity = postService.create(userId, createRequest);
        String name = postEntity.getUser() != null ? postEntity.getUser().getName() : null;
        PostCreateResponseDto postCreateResponseDto = PostCreateResponseDto.from(postEntity, name);
        return ResponseEntity.status(HttpStatus.CREATED).body(postCreateResponseDto);
    }

    @Operation(summary = "개별 게시글 조회")
    @GetMapping("/{postId}")
    public ResponseEntity<PostGetResponseDto> getPost(@PathVariable Long postId) {
        PostEntity postEntity = postService.getPost(postId);
        String name = postEntity.getUser() != null ? postEntity.getUser().getName() : null;
        PostGetResponseDto postGetResponseDto = PostGetResponseDto.from(postEntity, name);
        return ResponseEntity.ok(postGetResponseDto);
    }

    @Operation(summary = "게시글 리스트 조회")
    @GetMapping("/list")
    public ResponseEntity<List<PostGetResponseDto>> getPostList() {
        List<PostGetResponseDto> postGetResponseDto = postService.getPostList();
        return ResponseEntity.ok(postGetResponseDto);
    }

    @Operation(summary = "게시글 수정")
    @PutMapping("/{postId}")
    public ResponseEntity<PostUpdateResponseDto> update(@PathVariable Long postId,
                                                        @RequestParam Long userId,
                                                        @Valid @RequestBody PostUpdateRequestDto updateRequest) {
        PostEntity postEntity = postService.update(postId, userId, updateRequest);
        PostUpdateResponseDto postUpdateResponseDto = PostUpdateResponseDto.from(postEntity);
        return ResponseEntity.ok(postUpdateResponseDto);
    }

    @Operation(summary = "게시글 삭제")
    @DeleteMapping("/{postId}")
    public ResponseEntity<PostDeleteResponseDto> delete(@PathVariable Long postId,
                                                        @RequestParam Long userId) {
        postService.delete(postId, userId);
        return ResponseEntity.noContent().build();
    }
}
