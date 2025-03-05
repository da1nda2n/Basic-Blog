package com.example.blog.post.dto.res;

import com.example.blog.post.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDeleteResponseDto {
    private String message;

    public static PostDeleteResponseDto from(PostEntity post) {
        return PostDeleteResponseDto.builder()
                .message("게시글 삭제 완료")
                .build();
    }
}
