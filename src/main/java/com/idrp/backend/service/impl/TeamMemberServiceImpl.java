package com.idrp.backend.service.impl;

import com.idrp.backend.dto.teammember.TeamMemberRequestDto;
import com.idrp.backend.dto.teammember.TeamMemberResponseDto;
import com.idrp.backend.entity.TeamMember;
import com.idrp.backend.exception.DuplicateResourceException;
import com.idrp.backend.exception.ResourceNotFoundException;
import com.idrp.backend.repository.TeamMemberRepository;
import com.idrp.backend.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    @Override
    public TeamMemberResponseDto createTeamMember(TeamMemberRequestDto requestDto) {

        if (teamMemberRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateResourceException("Team member already exists with email: " + requestDto.getEmail());
        }

        TeamMember teamMember = mapToEntity(requestDto);
        TeamMember savedTeamMember = teamMemberRepository.save(teamMember);

        return mapToResponseDto(savedTeamMember);
    }

    @Override
    public Page<TeamMemberResponseDto> getAllTeamMembers(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Order.asc("displayOrder"), Sort.Order.desc("createdAt"))
        );

        return teamMemberRepository.findAll(pageable)
                .map(this::mapToResponseDto);
    }

    @Override
    public TeamMemberResponseDto getTeamMemberById(Long id) {
        TeamMember teamMember = teamMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team member not found with id: " + id));

        return mapToResponseDto(teamMember);
    }

    @Override
    public TeamMemberResponseDto updateTeamMember(Long id, TeamMemberRequestDto requestDto) {

        TeamMember existingTeamMember = teamMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team member not found with id: " + id));

        if (teamMemberRepository.existsByEmailAndIdNot(requestDto.getEmail(), id)) {
            throw new DuplicateResourceException("Another team member exists with email: " + requestDto.getEmail());
        }

        existingTeamMember.setName(requestDto.getName());
        existingTeamMember.setDesignation(requestDto.getDesignation());
        existingTeamMember.setDepartment(requestDto.getDepartment());
        existingTeamMember.setBio(requestDto.getBio());
        existingTeamMember.setEmail(requestDto.getEmail());
        existingTeamMember.setPhone(requestDto.getPhone());
        existingTeamMember.setProfileImageUrl(requestDto.getProfileImageUrl());
        existingTeamMember.setLinkedinUrl(requestDto.getLinkedinUrl());
        existingTeamMember.setDisplayOrder(
                requestDto.getDisplayOrder() != null ? requestDto.getDisplayOrder() : existingTeamMember.getDisplayOrder()
        );
        existingTeamMember.setActive(
                requestDto.getActive() != null ? requestDto.getActive() : existingTeamMember.getActive()
        );

        TeamMember updatedTeamMember = teamMemberRepository.save(existingTeamMember);

        return mapToResponseDto(updatedTeamMember);
    }

    @Override
    public void deleteTeamMember(Long id) {
        TeamMember teamMember = teamMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team member not found with id: " + id));

        teamMemberRepository.delete(teamMember);
    }

    private TeamMember mapToEntity(TeamMemberRequestDto dto) {
        return TeamMember.builder()
                .name(dto.getName())
                .designation(dto.getDesignation())
                .department(dto.getDepartment())
                .bio(dto.getBio())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .profileImageUrl(dto.getProfileImageUrl())
                .linkedinUrl(dto.getLinkedinUrl())
                .displayOrder(dto.getDisplayOrder() != null ? dto.getDisplayOrder() : 0)
                .active(dto.getActive() != null ? dto.getActive() : true)
                .build();
    }

    private TeamMemberResponseDto mapToResponseDto(TeamMember teamMember) {
        return TeamMemberResponseDto.builder()
                .id(teamMember.getId())
                .name(teamMember.getName())
                .designation(teamMember.getDesignation())
                .department(teamMember.getDepartment())
                .bio(teamMember.getBio())
                .email(teamMember.getEmail())
                .phone(teamMember.getPhone())
                .profileImageUrl(teamMember.getProfileImageUrl())
                .linkedinUrl(teamMember.getLinkedinUrl())
                .displayOrder(teamMember.getDisplayOrder())
                .active(teamMember.getActive())
                .createdAt(teamMember.getCreatedAt())
                .updatedAt(teamMember.getUpdatedAt())
                .build();
    }
}