package com.example.demo.mapper;

import java.util.Iterator;
import java.util.Set;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.model.Role;
import com.example.demo.model.User;

public final class UserMapper {

    public static UserResponse toResponse(User user) {
        if (user == null) return null;

        Set<Role> roles = user.getAuthorities();
        Role firstElement = new Role();
        if (!roles.isEmpty()) {
            Iterator<Role> iterator = roles.iterator();
            firstElement = iterator.next();
        }

        return UserResponse.builder()
                .email(user.getEmail())
                .role(firstElement.getAuthority())
                .build();
    }

    public static User toEntity(UserRequest dto) {
        if (dto == null) return null;

        User user = User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
        return user;
    }

    public static void copyToEntity(UserRequest dto, User entity) {
        if (dto == null || entity == null) return;
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
    }
}
