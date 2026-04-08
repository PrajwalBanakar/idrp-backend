package com.idrp.backend.service.impl;

import com.idrp.backend.dto.startup.StartupRequestDto;
import com.idrp.backend.dto.startup.StartupResponseDto;
import com.idrp.backend.entity.Startup;
import com.idrp.backend.exception.DuplicateResourceException;
import com.idrp.backend.exception.ResourceNotFoundException;
import com.idrp.backend.repository.StartupRepository;
import com.idrp.backend.service.StartupService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StartupServiceImpl implements StartupService {

    private final StartupRepository startupRepository;

    @Override
    public StartupResponseDto createStartup(StartupRequestDto requestDto) {
        if (startupRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateResourceException("Startup already exists with email: " + requestDto.getEmail());
        }

        Startup startup = mapToEntity(requestDto);
        Startup savedStartup = startupRepository.save(startup);
        return mapToResponseDto(savedStartup);
    }

    @Override
    public Page<StartupResponseDto> getAllStartups(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        return startupRepository.findAll(pageable)
                .map(this::mapToResponseDto);
    }

    @Override
    public StartupResponseDto getStartupById(Long id) {
        Startup startup = startupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Startup not found with id: " + id));

        return mapToResponseDto(startup);
    }

    @Override
    public StartupResponseDto updateStartup(Long id, StartupRequestDto requestDto) {
        Startup existingStartup = startupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Startup not found with id: " + id));

        if (startupRepository.existsByEmailAndIdNot(requestDto.getEmail(), id)) {
            throw new DuplicateResourceException("Another startup already exists with email: " + requestDto.getEmail());
        }

        existingStartup.setStartupName(requestDto.getStartupName());
        existingStartup.setFounderName(requestDto.getFounderName());
        existingStartup.setEmail(requestDto.getEmail());
        existingStartup.setPhone(requestDto.getPhone());
        existingStartup.setSector(requestDto.getSector());
        existingStartup.setDescription(requestDto.getDescription());

        Startup updatedStartup = startupRepository.save(existingStartup);
        return mapToResponseDto(updatedStartup);
    }

    @Override
    public void deleteStartup(Long id) {
        Startup startup = startupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Startup not found with id: " + id));

        startupRepository.delete(startup);
    }

    private Startup mapToEntity(StartupRequestDto dto) {
        return Startup.builder()
                .startupName(dto.getStartupName())
                .founderName(dto.getFounderName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .sector(dto.getSector())
                .description(dto.getDescription())
                .build();
    }

    private StartupResponseDto mapToResponseDto(Startup startup) {
        return StartupResponseDto.builder()
                .id(startup.getId())
                .startupName(startup.getStartupName())
                .founderName(startup.getFounderName())
                .email(startup.getEmail())
                .phone(startup.getPhone())
                .sector(startup.getSector())
                .description(startup.getDescription())
                .createdAt(startup.getCreatedAt())
                .updatedAt(startup.getUpdatedAt())
                .build();
    }
}