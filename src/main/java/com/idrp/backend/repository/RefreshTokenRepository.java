package com.idrp.backend.repository;

import com.idrp.backend.entity.RefreshToken;
import com.idrp.backend.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByAdmin(Admin admin);

    void deleteByAdmin(Admin admin);
}