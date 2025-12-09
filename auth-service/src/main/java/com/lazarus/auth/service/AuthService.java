package com.lazarus.auth.service;

import com.lazarus.auth.dto.auth.LoginRequest;
import com.lazarus.auth.dto.auth.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

    LoginResponse login(LoginRequest request, HttpServletRequest httpRequest);

    void logout(String username);
}
