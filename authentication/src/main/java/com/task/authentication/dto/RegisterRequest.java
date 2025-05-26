package com.task.authentication.dto;

import lombok.Data;

import java.util.Set;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private Set<String> roles; // Strings like "USER", "ADMIN"
}