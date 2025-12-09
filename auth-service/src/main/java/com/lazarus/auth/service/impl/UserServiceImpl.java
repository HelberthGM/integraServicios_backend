package com.lazarus.auth.service.impl;

import com.lazarus.auth.dto.user.UserRequestDTO;
import com.lazarus.auth.dto.user.UserResponseDTO;
import com.lazarus.auth.dto.user.UserUpdateDTO;
import com.lazarus.auth.exception.DuplicateResourceException;
import com.lazarus.auth.exception.ResourceNotFoundException;
import com.lazarus.auth.mapper.UserMapper;
import com.lazarus.auth.model.UserAccount;
import com.lazarus.auth.model.UserRole;
import com.lazarus.auth.repository.UserAccountRepository;
import com.lazarus.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserAccountRepository userAccountRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDTO> findAll() {
        return userAccountRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public UserResponseDTO findById(UUID id) {
        UserAccount user = userAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toDTO(user);
    }

    @Override
    public UserResponseDTO findByUsername(String username) {
        UserAccount user = userAccountRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        return userMapper.toDTO(user);
    }

    @Override
    public List<UserResponseDTO> findByRole(UserRole role) {
        return userAccountRepository.findByRole(role)
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public UserResponseDTO create(UserRequestDTO dto) {
        // Check if username already exists
        if (userAccountRepository.existsByUsername(dto.username())) {
            throw new DuplicateResourceException("Username already exists: " + dto.username());
        }

        // Map DTO to entity
        UserAccount user = userMapper.toEntity(dto);

        // Hash password
        user.setPasswordHash(passwordEncoder.encode(dto.password()));

        // Set default active if not provided
        if (user.getActive() == null) {
            user.setActive(true);
        }

        // Save user
        UserAccount savedUser = userAccountRepository.save(user);

        log.info("User created: {}", savedUser.getUsername());

        return userMapper.toDTO(savedUser);
    }

    @Override
    @Transactional
    public UserResponseDTO update(UUID id, UserUpdateDTO dto) {
        UserAccount user = userAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        // Update only provided fields
        userMapper.updateEntityFromDTO(dto, user);

        UserAccount updatedUser = userAccountRepository.save(user);

        log.info("User updated: {}", updatedUser.getUsername());

        return userMapper.toDTO(updatedUser);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!userAccountRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        userAccountRepository.deleteById(id);

        log.info("User deleted with id: {}", id);
    }

    @Override
    @Transactional
    public void changePassword(UUID id, String newPassword) {
        UserAccount user = userAccountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setPasswordHash(passwordEncoder.encode(newPassword));
        userAccountRepository.save(user);

        log.info("Password changed for user: {}", user.getUsername());
    }
}
