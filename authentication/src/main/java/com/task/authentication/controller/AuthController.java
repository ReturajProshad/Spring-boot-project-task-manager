package com.task.authentication.controller;

import com.task.authentication.dto.ApiResponse;
import com.task.authentication.dto.AuthRequest;
import com.task.authentication.dto.AuthResponse;
import com.task.authentication.dto.RegisterRequest;
import com.task.authentication.entity.User;
import com.task.authentication.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@RequestBody RegisterRequest request) {
        ApiResponse<User> response=new ApiResponse<>(true,"Registration Successful",authService.register(request));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody AuthRequest request) {
        String token = authService.login(request);
        ApiResponse<AuthResponse> response=new ApiResponse<>(
                true,"Successfully logged in",
                new AuthResponse(token)
        );
        return ResponseEntity.ok(response);
    }

}