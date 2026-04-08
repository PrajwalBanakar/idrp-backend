package com.idrp.backend.service;

import com.idrp.backend.dto.boardmember.BoardMemberRequestDto;
import com.idrp.backend.dto.boardmember.BoardMemberResponseDto;
import org.springframework.data.domain.Page;

public interface BoardMemberService {

    BoardMemberResponseDto createBoardMember(BoardMemberRequestDto requestDto);

    Page<BoardMemberResponseDto> getAllBoardMembers(int page, int size);

    BoardMemberResponseDto getBoardMemberById(Long id);

    BoardMemberResponseDto updateBoardMember(Long id, BoardMemberRequestDto requestDto);

    void deleteBoardMember(Long id);
}