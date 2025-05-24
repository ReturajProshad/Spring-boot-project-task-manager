package com.task.authentication.mapper;


import com.task.authentication.dto.UserResponseDTO;
import com.task.authentication.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserResponseDTO toDto(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRoles(user.getRoles().stream()
                .map(Enum::name)
                .collect(Collectors.toSet()));
        return dto;
    }
}