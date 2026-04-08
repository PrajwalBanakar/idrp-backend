package com.idrp.backend.controller;

import com.idrp.backend.dto.boardmember.BoardMemberRequestDto;
import com.idrp.backend.dto.boardmember.BoardMemberResponseDto;
import com.idrp.backend.dto.common.ApiResponse;
import com.idrp.backend.service.BoardMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board-members")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BoardMemberController {

    private final BoardMemberService boardMemberService;

    @PostMapping
    public ResponseEntity<ApiResponse<BoardMemberResponseDto>> createBoardMember(
            @Valid @RequestBody BoardMemberRequestDto requestDto
    ) {
        BoardMemberResponseDto boardMember = boardMemberService.createBoardMember(requestDto);

        return new ResponseEntity<>(
                ApiResponse.<BoardMemberResponseDto>builder()
                        .success(true)
                        .message("Board member created successfully")
                        .data(boardMember)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<BoardMemberResponseDto>>> getAllBoardMembers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<BoardMemberResponseDto> boardMembers = boardMemberService.getAllBoardMembers(page, size);

        return ResponseEntity.ok(
                ApiResponse.<Page<BoardMemberResponseDto>>builder()
                        .success(true)
                        .message("Board members fetched successfully")
                        .data(boardMembers)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BoardMemberResponseDto>> getBoardMemberById(@PathVariable Long id) {
        BoardMemberResponseDto boardMember = boardMemberService.getBoardMemberById(id);

        return ResponseEntity.ok(
                ApiResponse.<BoardMemberResponseDto>builder()
                        .success(true)
                        .message("Board member fetched successfully")
                        .data(boardMember)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BoardMemberResponseDto>> updateBoardMember(
            @PathVariable Long id,
            @Valid @RequestBody BoardMemberRequestDto requestDto
    ) {
        BoardMemberResponseDto boardMember = boardMemberService.updateBoardMember(id, requestDto);

        return ResponseEntity.ok(
                ApiResponse.<BoardMemberResponseDto>builder()
                        .success(true)
                        .message("Board member updated successfully")
                        .data(boardMember)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBoardMember(@PathVariable Long id) {
        boardMemberService.deleteBoardMember(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Board member deleted successfully")
                        .data(null)
                        .build()
        );
    }
}