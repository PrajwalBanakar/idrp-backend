package com.idrp.backend.service;

import com.idrp.backend.dto.teammember.TeamMemberRequestDto;
import com.idrp.backend.dto.teammember.TeamMemberResponseDto;
import org.springframework.data.domain.Page;

public interface TeamMemberService {

    TeamMemberResponseDto createTeamMember(TeamMemberRequestDto requestDto);

    Page<TeamMemberResponseDto> getAllTeamMembers(int page, int size);

    TeamMemberResponseDto getTeamMemberById(Long id);

    TeamMemberResponseDto updateTeamMember(Long id, TeamMemberRequestDto requestDto);

    void deleteTeamMember(Long id);
}