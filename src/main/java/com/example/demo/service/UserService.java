package com.example.demo.service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserResponse create(UserRequest request) {
        String role = request.getRole();
        Role userRole = roleRepository.findByAuthority(role).orElseThrow(() -> new NoSuchElementException("Authority not present"));
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        User user = UserMapper.toEntity(request);
        user.setAuthorities(authorities);
        User saved = repository.save(user);
        return UserMapper.toResponse(saved);
    }
}
