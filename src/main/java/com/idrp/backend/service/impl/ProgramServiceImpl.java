package com.idrp.backend.service.impl;

import com.idrp.backend.dto.program.ProgramRequestDto;
import com.idrp.backend.dto.program.ProgramResponseDto;
import com.idrp.backend.entity.Program;
import com.idrp.backend.exception.DuplicateResourceException;
import com.idrp.backend.exception.ResourceNotFoundException;
import com.idrp.backend.repository.ProgramRepository;
import com.idrp.backend.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {

    private final ProgramRepository programRepository;

    @Override
    public ProgramResponseDto createProgram(ProgramRequestDto requestDto) {
        if (programRepository.existsByProgramName(requestDto.getProgramName())) {
            throw new DuplicateResourceException("Program already exists with name: " + requestDto.getProgramName());
        }

        Program program = mapToEntity(requestDto);
        Program savedProgram = programRepository.save(program);
        return mapToResponseDto(savedProgram);
    }

    @Override
    public Page<ProgramResponseDto> getAllPrograms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        return programRepository.findAll(pageable)
                .map(this::mapToResponseDto);
    }

    @Override
    public ProgramResponseDto getProgramById(Long id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with id: " + id));

        return mapToResponseDto(program);
    }

    @Override
    public ProgramResponseDto updateProgram(Long id, ProgramRequestDto requestDto) {
        Program existingProgram = programRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with id: " + id));

        if (programRepository.existsByProgramNameAndIdNot(requestDto.getProgramName(), id)) {
            throw new DuplicateResourceException("Another program already exists with name: " + requestDto.getProgramName());
        }

        existingProgram.setProgramName(requestDto.getProgramName());
        existingProgram.setCategory(requestDto.getCategory());
        existingProgram.setDuration(requestDto.getDuration());
        existingProgram.setMode(requestDto.getMode());
        existingProgram.setEligibility(requestDto.getEligibility());
        existingProgram.setDescription(requestDto.getDescription());

        Program updatedProgram = programRepository.save(existingProgram);
        return mapToResponseDto(updatedProgram);
    }

    @Override
    public void deleteProgram(Long id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with id: " + id));

        programRepository.delete(program);
    }

    private Program mapToEntity(ProgramRequestDto dto) {
        return Program.builder()
                .programName(dto.getProgramName())
                .category(dto.getCategory())
                .duration(dto.getDuration())
                .mode(dto.getMode())
                .eligibility(dto.getEligibility())
                .description(dto.getDescription())
                .build();
    }

    private ProgramResponseDto mapToResponseDto(Program program) {
        return ProgramResponseDto.builder()
                .id(program.getId())
                .programName(program.getProgramName())
                .category(program.getCategory())
                .duration(program.getDuration())
                .mode(program.getMode())
                .eligibility(program.getEligibility())
                .description(program.getDescription())
                .createdAt(program.getCreatedAt())
                .updatedAt(program.getUpdatedAt())
                .build();
    }
}