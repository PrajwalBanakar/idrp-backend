package com.idrp.backend.service.impl;

import com.idrp.backend.dto.programapplication.ProgramApplicationRequestDto;
import com.idrp.backend.dto.programapplication.ProgramApplicationResponseDto;
import com.idrp.backend.entity.Program;
import com.idrp.backend.entity.ProgramApplication;
import com.idrp.backend.exception.DuplicateResourceException;
import com.idrp.backend.exception.ResourceNotFoundException;
import com.idrp.backend.repository.ProgramApplicationRepository;
import com.idrp.backend.repository.ProgramRepository;
import com.idrp.backend.service.ProgramApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramApplicationServiceImpl implements ProgramApplicationService {

    private final ProgramApplicationRepository programApplicationRepository;
    private final ProgramRepository programRepository;

    @Override
    public ProgramApplicationResponseDto createProgramApplication(ProgramApplicationRequestDto requestDto) {
        Program program = programRepository.findById(requestDto.getProgramId())
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with id: " + requestDto.getProgramId()));

        if (programApplicationRepository.existsByEmailAndProgramId(requestDto.getEmail(), requestDto.getProgramId())) {
            throw new DuplicateResourceException(
                    "Applicant already applied for this program with email: " + requestDto.getEmail()
            );
        }

        ProgramApplication programApplication = mapToEntity(requestDto, program);
        ProgramApplication savedProgramApplication = programApplicationRepository.save(programApplication);

        return mapToResponseDto(savedProgramApplication);
    }

    @Override
    public Page<ProgramApplicationResponseDto> getAllProgramApplications(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        return programApplicationRepository.findAll(pageable)
                .map(this::mapToResponseDto);
    }

    @Override
    public ProgramApplicationResponseDto getProgramApplicationById(Long id) {
        ProgramApplication programApplication = programApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Program application not found with id: " + id));

        return mapToResponseDto(programApplication);
    }

    @Override
    public ProgramApplicationResponseDto updateProgramApplication(Long id, ProgramApplicationRequestDto requestDto) {
        ProgramApplication existingProgramApplication = programApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Program application not found with id: " + id));

        Program program = programRepository.findById(requestDto.getProgramId())
                .orElseThrow(() -> new ResourceNotFoundException("Program not found with id: " + requestDto.getProgramId()));

        if (programApplicationRepository.existsByEmailAndProgramIdAndIdNot(
                requestDto.getEmail(),
                requestDto.getProgramId(),
                id
        )) {
            throw new DuplicateResourceException(
                    "Another application already exists for this program with email: " + requestDto.getEmail()
            );
        }

        existingProgramApplication.setApplicantName(requestDto.getApplicantName());
        existingProgramApplication.setEmail(requestDto.getEmail());
        existingProgramApplication.setPhone(requestDto.getPhone());
        existingProgramApplication.setOrganization(requestDto.getOrganization());
        existingProgramApplication.setIdeaTitle(requestDto.getIdeaTitle());
        existingProgramApplication.setStage(requestDto.getStage());
        existingProgramApplication.setMessage(requestDto.getMessage());
        existingProgramApplication.setProgram(program);

        ProgramApplication updatedProgramApplication = programApplicationRepository.save(existingProgramApplication);
        return mapToResponseDto(updatedProgramApplication);
    }

    @Override
    public void deleteProgramApplication(Long id) {
        ProgramApplication programApplication = programApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Program application not found with id: " + id));

        programApplicationRepository.delete(programApplication);
    }

    private ProgramApplication mapToEntity(ProgramApplicationRequestDto dto, Program program) {
        return ProgramApplication.builder()
                .applicantName(dto.getApplicantName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .organization(dto.getOrganization())
                .ideaTitle(dto.getIdeaTitle())
                .stage(dto.getStage())
                .message(dto.getMessage())
                .program(program)
                .build();
    }

    private ProgramApplicationResponseDto mapToResponseDto(ProgramApplication programApplication) {
        return ProgramApplicationResponseDto.builder()
                .id(programApplication.getId())
                .applicantName(programApplication.getApplicantName())
                .email(programApplication.getEmail())
                .phone(programApplication.getPhone())
                .organization(programApplication.getOrganization())
                .ideaTitle(programApplication.getIdeaTitle())
                .stage(programApplication.getStage())
                .message(programApplication.getMessage())
                .programId(programApplication.getProgram().getId())
                .programName(programApplication.getProgram().getProgramName())
                .createdAt(programApplication.getCreatedAt())
                .updatedAt(programApplication.getUpdatedAt())
                .build();
    }
}