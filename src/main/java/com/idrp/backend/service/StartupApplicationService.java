package com.idrp.backend.service;

import com.idrp.backend.dto.startupapplication.StartupApplicationRequestDto;
import com.idrp.backend.dto.startupapplication.StartupApplicationResponseDto;
import org.springframework.data.domain.Page;

public interface StartupApplicationService {

    StartupApplicationResponseDto createStartupApplication(StartupApplicationRequestDto requestDto);

    Page<StartupApplicationResponseDto> getAllStartupApplications(int page, int size);

    StartupApplicationResponseDto getStartupApplicationById(Long id);

    StartupApplicationResponseDto updateStartupApplication(Long id, StartupApplicationRequestDto requestDto);

    void deleteStartupApplication(Long id);
}