package com.lazarus.auth.service;

import com.lazarus.auth.dto.user.UserRequestDTO;
import com.lazarus.auth.dto.user.UserResponseDTO;
import com.lazarus.auth.dto.user.UserUpdateDTO;
import com.lazarus.auth.model.UserRole;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserResponseDTO> findAll();

    UserResponseDTO findById(UUID id);

    UserResponseDTO findByUsername(String username);

    List<UserResponseDTO> findByRole(UserRole role);

    UserResponseDTO create(UserRequestDTO dto);

    UserResponseDTO update(UUID id, UserUpdateDTO dto);

    void delete(UUID id);

    void changePassword(UUID id, String newPassword);
}
