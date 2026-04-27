package com.idrp.backend.service.impl;

import com.idrp.backend.entity.Admin;
import com.idrp.backend.entity.RefreshToken;
import com.idrp.backend.exception.ResourceNotFoundException;
import com.idrp.backend.exception.TokenExpiredException;
import com.idrp.backend.exception.TokenRevokedException;
import com.idrp.backend.repository.RefreshTokenRepository;
import com.idrp.backend.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private static final long REFRESH_TOKEN_VALIDITY_DAYS = 7;

    @Override
    public RefreshToken createRefreshToken(Admin admin) {

        refreshTokenRepository.deleteByAdmin(admin);

        RefreshToken refreshToken = RefreshToken.builder()
                .admin(admin)
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusDays(REFRESH_TOKEN_VALIDITY_DAYS))
                .revoked(false)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken validateRefreshToken(String token) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh token not found"));

        if (refreshToken.isRevoked()) {
            throw new TokenRevokedException("Refresh token has been revoked. Please login again.");
        }

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new TokenExpiredException("Refresh token has expired. Please login again.");
        }

        return refreshToken;
    }

    @Override
    public void revokeRefreshToken(String token) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh token not found"));

        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void deleteByAdmin(Admin admin) {
        refreshTokenRepository.deleteByAdmin(admin);
    }
}