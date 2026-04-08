package com.idrp.backend.service.impl;

import com.idrp.backend.dto.startupapplication.StartupApplicationRequestDto;
import com.idrp.backend.dto.startupapplication.StartupApplicationResponseDto;
import com.idrp.backend.entity.StartupApplication;
import com.idrp.backend.entity.StartupApplicationStatus;
import com.idrp.backend.exception.DuplicateResourceException;
import com.idrp.backend.exception.ResourceNotFoundException;
import com.idrp.backend.repository.StartupApplicationRepository;
import com.idrp.backend.service.StartupApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StartupApplicationServiceImpl implements StartupApplicationService {

    private final StartupApplicationRepository startupApplicationRepository;

    @Override
    public StartupApplicationResponseDto createStartupApplication(StartupApplicationRequestDto requestDto) {

        if (startupApplicationRepository.existsByStartupNameAndEmail(
                requestDto.getStartupName(),
                requestDto.getEmail()
        )) {
            throw new DuplicateResourceException(
                    "Startup application already exists for startup: " +
                            requestDto.getStartupName() + " and email: " + requestDto.getEmail()
            );
        }

        StartupApplication startupApplication = mapToEntity(requestDto);
        StartupApplication savedStartupApplication = startupApplicationRepository.save(startupApplication);

        return mapToResponseDto(savedStartupApplication);
    }

    @Override
    public Page<StartupApplicationResponseDto> getAllStartupApplications(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Order.desc("submittedAt"))
        );

        return startupApplicationRepository.findAll(pageable)
                .map(this::mapToResponseDto);
    }

    @Override
    public StartupApplicationResponseDto getStartupApplicationById(Long id) {
        StartupApplication startupApplication = startupApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Startup application not found with id: " + id));

        return mapToResponseDto(startupApplication);
    }

    @Override
    public StartupApplicationResponseDto updateStartupApplication(Long id, StartupApplicationRequestDto requestDto) {

        StartupApplication existingStartupApplication = startupApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Startup application not found with id: " + id));

        if (startupApplicationRepository.existsByStartupNameAndEmailAndIdNot(
                requestDto.getStartupName(),
                requestDto.getEmail(),
                id
        )) {
            throw new DuplicateResourceException(
                    "Another startup application already exists for startup: " +
                            requestDto.getStartupName() + " and email: " + requestDto.getEmail()
            );
        }

        existingStartupApplication.setStartupName(requestDto.getStartupName());
        existingStartupApplication.setFounderName(requestDto.getFounderName());
        existingStartupApplication.setEmail(requestDto.getEmail());
        existingStartupApplication.setPhone(requestDto.getPhone());
        existingStartupApplication.setSector(requestDto.getSector());
        existingStartupApplication.setStage(requestDto.getStage());
        existingStartupApplication.setProblemStatement(requestDto.getProblemStatement());
        existingStartupApplication.setSolution(requestDto.getSolution());
        existingStartupApplication.setWebsiteUrl(requestDto.getWebsiteUrl());
        existingStartupApplication.setPitchDeckUrl(requestDto.getPitchDeckUrl());
        existingStartupApplication.setStatus(
                requestDto.getStatus() != null ? requestDto.getStatus() : existingStartupApplication.getStatus()
        );

        StartupApplication updatedStartupApplication = startupApplicationRepository.save(existingStartupApplication);

        return mapToResponseDto(updatedStartupApplication);
    }

    private StartupApplication mapToEntity(StartupApplicationRequestDto dto) {
        return StartupApplication.builder()
                .startupName(dto.getStartupName())
                .founderName(dto.getFounderName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .sector(dto.getSector())
                .stage(dto.getStage())
                .problemStatement(dto.getProblemStatement())
                .solution(dto.getSolution())
                .websiteUrl(dto.getWebsiteUrl())
                .pitchDeckUrl(dto.getPitchDeckUrl())
                .status(dto.getStatus() != null ? dto.getStatus() : StartupApplicationStatus.PENDING)
                .build();
    }

    private StartupApplicationResponseDto mapToResponseDto(StartupApplication startupApplication) {
        return StartupApplicationResponseDto.builder()
                .id(startupApplication.getId())
                .startupName(startupApplication.getStartupName())
                .founderName(startupApplication.getFounderName())
                .email(startupApplication.getEmail())
                .phone(startupApplication.getPhone())
                .sector(startupApplication.getSector())
                .stage(startupApplication.getStage())
                .problemStatement(startupApplication.getProblemStatement())
                .solution(startupApplication.getSolution())
                .websiteUrl(startupApplication.getWebsiteUrl())
                .pitchDeckUrl(startupApplication.getPitchDeckUrl())
                .status(startupApplication.getStatus())
                .submittedAt(startupApplication.getSubmittedAt())
                .updatedAt(startupApplication.getUpdatedAt())
                .build();
    }

    @Override
    public void deleteStartupApplication(Long id) {
        StartupApplication startupApplication = startupApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Startup application not found with id: " + id));

        startupApplicationRepository.delete(startupApplication);
    }
}