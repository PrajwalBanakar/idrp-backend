package com.idrp.backend.service.impl;

import com.idrp.backend.dto.auth.AdminLoginRequestDto;
import com.idrp.backend.dto.auth.AuthResponseDto;
import com.idrp.backend.dto.auth.CreateAdminRequestDto;
import com.idrp.backend.entity.Admin;
import com.idrp.backend.entity.AdminRole;
import com.idrp.backend.exception.DuplicateResourceException;
import com.idrp.backend.exception.ResourceNotFoundException;
import com.idrp.backend.repository.AdminRepository;
import com.idrp.backend.service.AuthService;
import com.idrp.backend.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDto createAdmin(CreateAdminRequestDto requestDto) {

        if (adminRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateResourceException("Admin already exists with email: " + requestDto.getEmail());
        }

        Admin admin = Admin.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .role(requestDto.getRole() != null ? requestDto.getRole() : AdminRole.ADMIN)
                .active(true)
                .build();

        Admin savedAdmin = adminRepository.save(admin);
        String token = jwtService.generateToken(savedAdmin);

        return AuthResponseDto.builder()
                .adminId(savedAdmin.getId())
                .name(savedAdmin.getName())
                .email(savedAdmin.getEmail())
                .role(savedAdmin.getRole())
                .token(token)
                .build();
    }

    @Override
    public AuthResponseDto login(AdminLoginRequestDto requestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getEmail(),
                        requestDto.getPassword()
                )
        );

        Admin admin = adminRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with email: " + requestDto.getEmail()));

        String token = jwtService.generateToken(admin);

        return AuthResponseDto.builder()
                .adminId(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail())
                .role(admin.getRole())
                .token(token)
                .build();
    }
}