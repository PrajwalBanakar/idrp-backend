package com.idrp.backend.service;

import com.idrp.backend.dto.program.ProgramRequestDto;
import com.idrp.backend.dto.program.ProgramResponseDto;
import org.springframework.data.domain.Page;

public interface ProgramService {

    ProgramResponseDto createProgram(ProgramRequestDto requestDto);

    Page<ProgramResponseDto> getAllPrograms(int page, int size);

    ProgramResponseDto getProgramById(Long id);

    ProgramResponseDto updateProgram(Long id, ProgramRequestDto requestDto);

    void deleteProgram(Long id);
}