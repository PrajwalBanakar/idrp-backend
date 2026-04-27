package com.idrp.backend.service;

import com.idrp.backend.dto.admin.AdminRequestDto;
import com.idrp.backend.dto.admin.AdminResponseDto;
import org.springframework.data.domain.Page;

public interface AdminService {

    AdminResponseDto createAdmin(AdminRequestDto requestDto);

    Page<AdminResponseDto> getAllAdmins(int page, int size);

    AdminResponseDto getAdminById(Long id);

    AdminResponseDto updateAdmin(Long id, AdminRequestDto requestDto);

    void deleteAdmin(Long id);
}