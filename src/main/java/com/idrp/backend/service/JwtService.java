package com.idrp.backend.service;

import com.idrp.backend.entity.Admin;
import io.jsonwebtoken.Claims;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    String generateToken(Admin admin);

    String generateToken(Map<String, Object> extraClaims, Admin admin);

    String extractUsername(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    boolean isTokenValid(String token, Admin admin);
}