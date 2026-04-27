package com.idrp.backend.controller;

import com.idrp.backend.dto.common.ApiResponse;
import com.idrp.backend.dto.teammember.TeamMemberRequestDto;
import com.idrp.backend.dto.teammember.TeamMemberResponseDto;
import com.idrp.backend.service.TeamMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team-members")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<TeamMemberResponseDto>> createTeamMember(
            @Valid @RequestBody TeamMemberRequestDto requestDto
    ) {
        TeamMemberResponseDto teamMember = teamMemberService.createTeamMember(requestDto);

        return new ResponseEntity<>(
                ApiResponse.<TeamMemberResponseDto>builder()
                        .success(true)
                        .message("Team member created successfully")
                        .data(teamMember)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<TeamMemberResponseDto>>> getAllTeamMembers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<TeamMemberResponseDto> teamMembers = teamMemberService.getAllTeamMembers(page, size);

        return ResponseEntity.ok(
                ApiResponse.<Page<TeamMemberResponseDto>>builder()
                        .success(true)
                        .message("Team members fetched successfully")
                        .data(teamMembers)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TeamMemberResponseDto>> getTeamMemberById(@PathVariable Long id) {
        TeamMemberResponseDto teamMember = teamMemberService.getTeamMemberById(id);

        return ResponseEntity.ok(
                ApiResponse.<TeamMemberResponseDto>builder()
                        .success(true)
                        .message("Team member fetched successfully")
                        .data(teamMember)
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TeamMemberResponseDto>> updateTeamMember(
            @PathVariable Long id,
            @Valid @RequestBody TeamMemberRequestDto requestDto
    ) {
        TeamMemberResponseDto teamMember = teamMemberService.updateTeamMember(id, requestDto);

        return ResponseEntity.ok(
                ApiResponse.<TeamMemberResponseDto>builder()
                        .success(true)
                        .message("Team member updated successfully")
                        .data(teamMember)
                        .build()
        );
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTeamMember(@PathVariable Long id) {
        teamMemberService.deleteTeamMember(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Team member deleted successfully")
                        .data(null)
                        .build()
        );
    }
}