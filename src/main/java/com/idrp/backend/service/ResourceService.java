package com.idrp.backend.service;

import com.idrp.backend.dto.resource.ResourceRequestDto;
import com.idrp.backend.dto.resource.ResourceResponseDto;
import org.springframework.data.domain.Page;

public interface ResourceService {

    ResourceResponseDto createResource(ResourceRequestDto requestDto);

    Page<ResourceResponseDto> getAllResources(int page, int size);

    ResourceResponseDto getResourceById(Long id);

    ResourceResponseDto updateResource(Long id, ResourceRequestDto requestDto);

    void deleteResource(Long id);
}