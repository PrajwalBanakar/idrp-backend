package com.idrp.backend.service;

import com.idrp.backend.dto.mentor.MentorRequestDto;
import com.idrp.backend.dto.mentor.MentorResponseDto;
import org.springframework.data.domain.Page;

public interface MentorService {

    MentorResponseDto createMentor(MentorRequestDto requestDto);

    Page<MentorResponseDto> getAllMentors(int page, int size);

    MentorResponseDto getMentorById(Long id);

    MentorResponseDto updateMentor(Long id, MentorRequestDto requestDto);

    void deleteMentor(Long id);
}