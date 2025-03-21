package com.example.blog.user.controller;

import com.example.blog.user.dto.req.UserLoginRequestDto;
import com.example.blog.user.dto.req.UserSignupRequestDto;
import com.example.blog.user.dto.req.UserUpdateRequestDto;
import com.example.blog.user.dto.res.UserGetResponseDto;
import com.example.blog.user.dto.res.UserLoginResponseDto;
import com.example.blog.user.dto.res.UserSignupResponseDto;
import com.example.blog.user.dto.res.UserUpdateResponseDto;
import com.example.blog.user.entity.UserEntity;
import com.example.blog.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<UserSignupResponseDto> signup(@Valid @RequestBody UserSignupRequestDto signupRequest) {
        UserEntity userEntity = userService.signup(signupRequest);
        UserSignupResponseDto responseDto = UserSignupResponseDto.from(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@Valid @RequestBody UserLoginRequestDto loginRequest) {
        UserEntity userEntity = userService.login(loginRequest);
        UserLoginResponseDto responseDto = UserLoginResponseDto.from(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Operation(summary = "개별 회원 조회")
    @GetMapping("/{userId}")
    public ResponseEntity<UserGetResponseDto> getUser(@PathVariable Long userId) {
        UserEntity userEntity = userService.getUser(userId);
        UserGetResponseDto responseDto = UserGetResponseDto.from(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Operation(summary = "전체 회원 조회")
    @GetMapping
    public ResponseEntity<List<UserGetResponseDto>> getUserList() {
        List<UserGetResponseDto> responseList = userService.getUserList();
        return ResponseEntity.status(HttpStatus.OK).body(responseList);
    }

    @Operation(summary = "회원 정보 수정")
    @PutMapping("/{userId}")
    public ResponseEntity<UserUpdateResponseDto> update(@PathVariable Long userId, @Valid @RequestBody UserUpdateRequestDto updateRequest) {
        UserEntity updatedUser = userService.update(userId, updateRequest);
        UserUpdateResponseDto responseDto = UserUpdateResponseDto.from(updatedUser);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

}
