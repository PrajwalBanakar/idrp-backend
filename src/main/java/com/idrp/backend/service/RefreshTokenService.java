package com.idrp.backend.service;

import com.idrp.backend.entity.Admin;
import com.idrp.backend.entity.RefreshToken;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(Admin admin);

    RefreshToken validateRefreshToken(String token);

    void revokeRefreshToken(String token);

    void deleteByAdmin(Admin admin);
}