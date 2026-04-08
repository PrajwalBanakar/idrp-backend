package com.idrp.backend.service;

import com.idrp.backend.dto.partner.PartnerRequestDto;
import com.idrp.backend.dto.partner.PartnerResponseDto;
import org.springframework.data.domain.Page;

public interface PartnerService {

    PartnerResponseDto createPartner(PartnerRequestDto requestDto);

    Page<PartnerResponseDto> getAllPartners(int page, int size);

    PartnerResponseDto getPartnerById(Long id);

    PartnerResponseDto updatePartner(Long id, PartnerRequestDto requestDto);

    void deletePartner(Long id);
}