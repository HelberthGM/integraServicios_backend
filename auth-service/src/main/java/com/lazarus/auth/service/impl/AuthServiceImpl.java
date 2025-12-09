package com.lazarus.auth.service.impl;

import com.lazarus.auth.dto.auth.LoginRequest;
import com.lazarus.auth.dto.auth.LoginResponse;
import com.lazarus.auth.exception.UnauthorizedException;
import com.lazarus.auth.mapper.AuthMapper;
import com.lazarus.auth.model.LoginLog;
import com.lazarus.auth.model.UserAccount;
import com.lazarus.auth.repository.LoginLogRepository;
import com.lazarus.auth.repository.UserAccountRepository;
import com.lazarus.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userAccountRepository;
    private final LoginLogRepository loginLogRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthMapper authMapper;

    @Override
    @Transactional
    public LoginResponse login(LoginRequest request, HttpServletRequest httpRequest) {
        // Find user
        UserAccount user = userAccountRepository.findByUsername(request.username())
                .orElseThrow(() -> new UnauthorizedException("Invalid username or password"));

        // Check if user is active
        if (!user.getActive()) {
            logLoginAttempt(user.getId(), false, httpRequest);
            throw new UnauthorizedException("User account is inactive");
        }

        // Verify password
        if (!passwordEncoder.matches(request.password(), user.getPasswordHash())) {
            logLoginAttempt(user.getId(), false, httpRequest);
            throw new UnauthorizedException("Invalid username or password");
        }

        // Log successful login
        logLoginAttempt(user.getId(), true, httpRequest);

        log.info("User {} logged in successfully", user.getUsername());

        return authMapper.toLoginResponse(user);
    }

    @Override
    public void logout(String username) {
        log.info("User {} logged out", username);
        // For basic auth, logout is stateless. Could be extended for token invalidation.
    }

    private void logLoginAttempt(UUID userId, boolean success, HttpServletRequest httpRequest) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(userId);
        loginLog.setSuccess(success);
        loginLog.setIpAddress(getClientIP(httpRequest));
        loginLog.setUserAgent(httpRequest.getHeader("User-Agent"));
        loginLogRepository.save(loginLog);
    }

    private String getClientIP(HttpServletRequest request) {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
