package com.idrp.backend.service;

import com.idrp.backend.dto.programapplication.ProgramApplicationRequestDto;
import com.idrp.backend.dto.programapplication.ProgramApplicationResponseDto;
import org.springframework.data.domain.Page;

public interface ProgramApplicationService {

    ProgramApplicationResponseDto createProgramApplication(ProgramApplicationRequestDto requestDto);

    Page<ProgramApplicationResponseDto> getAllProgramApplications(int page, int size);

    ProgramApplicationResponseDto getProgramApplicationById(Long id);

    ProgramApplicationResponseDto updateProgramApplication(Long id, ProgramApplicationRequestDto requestDto);

    void deleteProgramApplication(Long id);
}