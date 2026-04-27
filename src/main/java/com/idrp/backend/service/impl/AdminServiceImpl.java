package com.idrp.backend.service.impl;

import com.idrp.backend.dto.admin.AdminRequestDto;
import com.idrp.backend.dto.admin.AdminResponseDto;
import com.idrp.backend.entity.Admin;
import com.idrp.backend.entity.AdminRole;
import com.idrp.backend.exception.DuplicateResourceException;
import com.idrp.backend.exception.ResourceNotFoundException;
import com.idrp.backend.repository.AdminRepository;
import com.idrp.backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminResponseDto createAdmin(AdminRequestDto requestDto) {
        if (adminRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateResourceException("Admin already exists with email: " + requestDto.getEmail());
        }

        Admin admin = Admin.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .role(requestDto.getRole() != null ? requestDto.getRole() : AdminRole.ADMIN)
                .active(requestDto.getActive() != null ? requestDto.getActive() : true)
                .build();

        return mapToResponse(adminRepository.save(admin));
    }

    @Override
    public Page<AdminResponseDto> getAllAdmins(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        return adminRepository.findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    public AdminResponseDto getAdminById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + id));

        return mapToResponse(admin);
    }

    @Override
    public AdminResponseDto updateAdmin(Long id, AdminRequestDto requestDto) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + id));

        if (!admin.getEmail().equals(requestDto.getEmail()) && adminRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateResourceException("Admin already exists with email: " + requestDto.getEmail());
        }

        admin.setName(requestDto.getName());
        admin.setEmail(requestDto.getEmail());

        if (requestDto.getPassword() != null && !requestDto.getPassword().isBlank()) {
            admin.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        }

        if (requestDto.getRole() != null) {
            admin.setRole(requestDto.getRole());
        }

        if (requestDto.getActive() != null) {
            admin.setActive(requestDto.getActive());
        }

        return mapToResponse(adminRepository.save(admin));
    }

    @Override
    public void deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Admin not found with id: " + id));

        adminRepository.delete(admin);
    }

    private AdminResponseDto mapToResponse(Admin admin) {
        return AdminResponseDto.builder()
                .id(admin.getId())
                .name(admin.getName())
                .email(admin.getEmail())
                .role(admin.getRole())
                .active(admin.getActive())
                .createdAt(admin.getCreatedAt())
                .updatedAt(admin.getUpdatedAt())
                .build();
    }
}