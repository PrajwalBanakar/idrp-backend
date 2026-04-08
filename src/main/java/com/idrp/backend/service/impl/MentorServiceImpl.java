package com.idrp.backend.service.impl;

import com.idrp.backend.dto.mentor.MentorRequestDto;
import com.idrp.backend.dto.mentor.MentorResponseDto;
import com.idrp.backend.entity.Mentor;
import com.idrp.backend.exception.DuplicateResourceException;
import com.idrp.backend.exception.ResourceNotFoundException;
import com.idrp.backend.repository.MentorRepository;
import com.idrp.backend.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MentorServiceImpl implements MentorService {

    private final MentorRepository mentorRepository;

    @Override
    public MentorResponseDto createMentor(MentorRequestDto requestDto) {

        if (mentorRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateResourceException("Mentor already exists with email: " + requestDto.getEmail());
        }

        Mentor mentor = mapToEntity(requestDto);
        Mentor savedMentor = mentorRepository.save(mentor);

        return mapToResponseDto(savedMentor);
    }

    @Override
    public Page<MentorResponseDto> getAllMentors(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        return mentorRepository.findAll(pageable)
                .map(this::mapToResponseDto);
    }

    @Override
    public MentorResponseDto getMentorById(Long id) {
        Mentor mentor = mentorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mentor not found with id: " + id));

        return mapToResponseDto(mentor);
    }

    @Override
    public MentorResponseDto updateMentor(Long id, MentorRequestDto requestDto) {

        Mentor existingMentor = mentorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mentor not found with id: " + id));

        if (mentorRepository.existsByEmailAndIdNot(requestDto.getEmail(), id)) {
            throw new DuplicateResourceException("Another mentor exists with email: " + requestDto.getEmail());
        }

        existingMentor.setName(requestDto.getName());
        existingMentor.setEmail(requestDto.getEmail());
        existingMentor.setPhone(requestDto.getPhone());
        existingMentor.setDesignation(requestDto.getDesignation());
        existingMentor.setOrganization(requestDto.getOrganization());
        existingMentor.setExpertise(requestDto.getExpertise());
        existingMentor.setBio(requestDto.getBio());
        existingMentor.setLinkedinUrl(requestDto.getLinkedinUrl());
        existingMentor.setActive(
                requestDto.getActive() != null ? requestDto.getActive() : existingMentor.getActive()
        );

        Mentor updatedMentor = mentorRepository.save(existingMentor);

        return mapToResponseDto(updatedMentor);
    }

    @Override
    public void deleteMentor(Long id) {
        Mentor mentor = mentorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mentor not found with id: " + id));

        mentorRepository.delete(mentor);
    }

    private Mentor mapToEntity(MentorRequestDto dto) {
        return Mentor.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .designation(dto.getDesignation())
                .organization(dto.getOrganization())
                .expertise(dto.getExpertise())
                .bio(dto.getBio())
                .linkedinUrl(dto.getLinkedinUrl())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .build();
    }

    private MentorResponseDto mapToResponseDto(Mentor mentor) {
        return MentorResponseDto.builder()
                .id(mentor.getId())
                .name(mentor.getName())
                .email(mentor.getEmail())
                .phone(mentor.getPhone())
                .designation(mentor.getDesignation())
                .organization(mentor.getOrganization())
                .expertise(mentor.getExpertise())
                .bio(mentor.getBio())
                .linkedinUrl(mentor.getLinkedinUrl())
                .active(mentor.getActive())
                .createdAt(mentor.getCreatedAt())
                .updatedAt(mentor.getUpdatedAt())
                .build();
    }
}