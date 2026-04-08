package com.idrp.backend.service.impl;

import com.idrp.backend.dto.boardmember.BoardMemberRequestDto;
import com.idrp.backend.dto.boardmember.BoardMemberResponseDto;
import com.idrp.backend.entity.BoardMember;
import com.idrp.backend.exception.DuplicateResourceException;
import com.idrp.backend.exception.ResourceNotFoundException;
import com.idrp.backend.repository.BoardMemberRepository;
import com.idrp.backend.service.BoardMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardMemberServiceImpl implements BoardMemberService {

    private final BoardMemberRepository boardMemberRepository;

    @Override
    public BoardMemberResponseDto createBoardMember(BoardMemberRequestDto requestDto) {

        if (boardMemberRepository.existsByEmail(requestDto.getEmail())) {
            throw new DuplicateResourceException("Board member already exists with email: " + requestDto.getEmail());
        }

        BoardMember boardMember = mapToEntity(requestDto);
        BoardMember savedBoardMember = boardMemberRepository.save(boardMember);

        return mapToResponseDto(savedBoardMember);
    }

    @Override
    public Page<BoardMemberResponseDto> getAllBoardMembers(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Order.asc("displayOrder"), Sort.Order.desc("createdAt"))
        );

        return boardMemberRepository.findAll(pageable)
                .map(this::mapToResponseDto);
    }

    @Override
    public BoardMemberResponseDto getBoardMemberById(Long id) {
        BoardMember boardMember = boardMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board member not found with id: " + id));

        return mapToResponseDto(boardMember);
    }

    @Override
    public BoardMemberResponseDto updateBoardMember(Long id, BoardMemberRequestDto requestDto) {

        BoardMember existingBoardMember = boardMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board member not found with id: " + id));

        if (boardMemberRepository.existsByEmailAndIdNot(requestDto.getEmail(), id)) {
            throw new DuplicateResourceException("Another board member exists with email: " + requestDto.getEmail());
        }

        existingBoardMember.setName(requestDto.getName());
        existingBoardMember.setDesignation(requestDto.getDesignation());
        existingBoardMember.setBoardType(requestDto.getBoardType());
        existingBoardMember.setOrganization(requestDto.getOrganization());
        existingBoardMember.setBio(requestDto.getBio());
        existingBoardMember.setEmail(requestDto.getEmail());
        existingBoardMember.setPhone(requestDto.getPhone());
        existingBoardMember.setProfileImageUrl(requestDto.getProfileImageUrl());
        existingBoardMember.setLinkedinUrl(requestDto.getLinkedinUrl());
        existingBoardMember.setDisplayOrder(
                requestDto.getDisplayOrder() != null ? requestDto.getDisplayOrder() : existingBoardMember.getDisplayOrder()
        );
        existingBoardMember.setActive(
                requestDto.getActive() != null ? requestDto.getActive() : existingBoardMember.getActive()
        );

        BoardMember updatedBoardMember = boardMemberRepository.save(existingBoardMember);

        return mapToResponseDto(updatedBoardMember);
    }

    @Override
    public void deleteBoardMember(Long id) {
        BoardMember boardMember = boardMemberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board member not found with id: " + id));

        boardMemberRepository.delete(boardMember);
    }

    private BoardMember mapToEntity(BoardMemberRequestDto dto) {
        return BoardMember.builder()
                .name(dto.getName())
                .designation(dto.getDesignation())
                .boardType(dto.getBoardType())
                .organization(dto.getOrganization())
                .bio(dto.getBio())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .profileImageUrl(dto.getProfileImageUrl())
                .linkedinUrl(dto.getLinkedinUrl())
                .displayOrder(dto.getDisplayOrder() != null ? dto.getDisplayOrder() : 0)
                .active(dto.getActive() != null ? dto.getActive() : true)
                .build();
    }

    private BoardMemberResponseDto mapToResponseDto(BoardMember boardMember) {
        return BoardMemberResponseDto.builder()
                .id(boardMember.getId())
                .name(boardMember.getName())
                .designation(boardMember.getDesignation())
                .boardType(boardMember.getBoardType())
                .organization(boardMember.getOrganization())
                .bio(boardMember.getBio())
                .email(boardMember.getEmail())
                .phone(boardMember.getPhone())
                .profileImageUrl(boardMember.getProfileImageUrl())
                .linkedinUrl(boardMember.getLinkedinUrl())
                .displayOrder(boardMember.getDisplayOrder())
                .active(boardMember.getActive())
                .createdAt(boardMember.getCreatedAt())
                .updatedAt(boardMember.getUpdatedAt())
                .build();
    }
}