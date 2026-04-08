package com.idrp.backend.service.impl;

import com.idrp.backend.dto.partner.PartnerRequestDto;
import com.idrp.backend.dto.partner.PartnerResponseDto;
import com.idrp.backend.entity.Partner;
import com.idrp.backend.exception.DuplicateResourceException;
import com.idrp.backend.exception.ResourceNotFoundException;
import com.idrp.backend.repository.PartnerRepository;
import com.idrp.backend.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;

    @Override
    public PartnerResponseDto createPartner(PartnerRequestDto requestDto) {

        if (partnerRepository.existsByName(requestDto.getName())) {
            throw new DuplicateResourceException("Partner already exists with name: " + requestDto.getName());
        }

        Partner partner = mapToEntity(requestDto);
        Partner savedPartner = partnerRepository.save(partner);

        return mapToResponseDto(savedPartner);
    }

    @Override
    public Page<PartnerResponseDto> getAllPartners(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        return partnerRepository.findAll(pageable)
                .map(this::mapToResponseDto);
    }

    @Override
    public PartnerResponseDto getPartnerById(Long id) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partner not found with id: " + id));

        return mapToResponseDto(partner);
    }

    @Override
    public PartnerResponseDto updatePartner(Long id, PartnerRequestDto requestDto) {

        Partner existingPartner = partnerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partner not found with id: " + id));

        if (partnerRepository.existsByNameAndIdNot(requestDto.getName(), id)) {
            throw new DuplicateResourceException("Another partner exists with name: " + requestDto.getName());
        }

        existingPartner.setName(requestDto.getName());
        existingPartner.setCategory(requestDto.getCategory());
        existingPartner.setWebsiteUrl(requestDto.getWebsiteUrl());
        existingPartner.setLogoUrl(requestDto.getLogoUrl());
        existingPartner.setDescription(requestDto.getDescription());
        existingPartner.setContactPerson(requestDto.getContactPerson());
        existingPartner.setContactEmail(requestDto.getContactEmail());
        existingPartner.setContactPhone(requestDto.getContactPhone());
        existingPartner.setActive(
                requestDto.getActive() != null ? requestDto.getActive() : existingPartner.getActive()
        );

        Partner updatedPartner = partnerRepository.save(existingPartner);

        return mapToResponseDto(updatedPartner);
    }

    @Override
    public void deletePartner(Long id) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partner not found with id: " + id));

        partnerRepository.delete(partner);
    }

    private Partner mapToEntity(PartnerRequestDto dto) {
        return Partner.builder()
                .name(dto.getName())
                .category(dto.getCategory())
                .websiteUrl(dto.getWebsiteUrl())
                .logoUrl(dto.getLogoUrl())
                .description(dto.getDescription())
                .contactPerson(dto.getContactPerson())
                .contactEmail(dto.getContactEmail())
                .contactPhone(dto.getContactPhone())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .build();
    }

    private PartnerResponseDto mapToResponseDto(Partner partner) {
        return PartnerResponseDto.builder()
                .id(partner.getId())
                .name(partner.getName())
                .category(partner.getCategory())
                .websiteUrl(partner.getWebsiteUrl())
                .logoUrl(partner.getLogoUrl())
                .description(partner.getDescription())
                .contactPerson(partner.getContactPerson())
                .contactEmail(partner.getContactEmail())
                .contactPhone(partner.getContactPhone())
                .active(partner.getActive())
                .createdAt(partner.getCreatedAt())
                .updatedAt(partner.getUpdatedAt())
                .build();
    }
}