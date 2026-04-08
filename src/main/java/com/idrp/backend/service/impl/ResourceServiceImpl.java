package com.idrp.backend.service.impl;

import com.idrp.backend.dto.resource.ResourceRequestDto;
import com.idrp.backend.dto.resource.ResourceResponseDto;
import com.idrp.backend.entity.Resource;
import com.idrp.backend.exception.DuplicateResourceException;
import com.idrp.backend.exception.ResourceNotFoundException;
import com.idrp.backend.repository.ResourceRepository;
import com.idrp.backend.service.ResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;

    @Override
    public ResourceResponseDto createResource(ResourceRequestDto requestDto) {

        if (resourceRepository.existsBySlug(requestDto.getSlug())) {
            throw new DuplicateResourceException("Resource already exists with slug: " + requestDto.getSlug());
        }

        Resource resource = mapToEntity(requestDto);
        Resource savedResource = resourceRepository.save(resource);

        return mapToResponseDto(savedResource);
    }

    @Override
    public Page<ResourceResponseDto> getAllResources(int page, int size) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Order.asc("displayOrder"), Sort.Order.desc("publishDate"), Sort.Order.desc("createdAt"))
        );

        return resourceRepository.findAll(pageable)
                .map(this::mapToResponseDto);
    }

    @Override
    public ResourceResponseDto getResourceById(Long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));

        return mapToResponseDto(resource);
    }

    @Override
    public ResourceResponseDto updateResource(Long id, ResourceRequestDto requestDto) {

        Resource existingResource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));

        if (resourceRepository.existsBySlugAndIdNot(requestDto.getSlug(), id)) {
            throw new DuplicateResourceException("Another resource exists with slug: " + requestDto.getSlug());
        }

        existingResource.setTitle(requestDto.getTitle());
        existingResource.setSlug(requestDto.getSlug());
        existingResource.setType(requestDto.getType());
        existingResource.setSummary(requestDto.getSummary());
        existingResource.setContent(requestDto.getContent());
        existingResource.setFileUrl(requestDto.getFileUrl());
        existingResource.setCoverImageUrl(requestDto.getCoverImageUrl());
        existingResource.setPublishDate(requestDto.getPublishDate());
        existingResource.setAuthor(requestDto.getAuthor());
        existingResource.setDisplayOrder(
                requestDto.getDisplayOrder() != null ? requestDto.getDisplayOrder() : existingResource.getDisplayOrder()
        );
        existingResource.setActive(
                requestDto.getActive() != null ? requestDto.getActive() : existingResource.getActive()
        );

        Resource updatedResource = resourceRepository.save(existingResource);

        return mapToResponseDto(updatedResource);
    }

    @Override
    public void deleteResource(Long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));

        resourceRepository.delete(resource);
    }

    private Resource mapToEntity(ResourceRequestDto dto) {
        return Resource.builder()
                .title(dto.getTitle())
                .slug(dto.getSlug())
                .type(dto.getType())
                .summary(dto.getSummary())
                .content(dto.getContent())
                .fileUrl(dto.getFileUrl())
                .coverImageUrl(dto.getCoverImageUrl())
                .publishDate(dto.getPublishDate())
                .author(dto.getAuthor())
                .displayOrder(dto.getDisplayOrder() != null ? dto.getDisplayOrder() : 0)
                .active(dto.getActive() != null ? dto.getActive() : true)
                .build();
    }

    private ResourceResponseDto mapToResponseDto(Resource resource) {
        return ResourceResponseDto.builder()
                .id(resource.getId())
                .title(resource.getTitle())
                .slug(resource.getSlug())
                .type(resource.getType())
                .summary(resource.getSummary())
                .content(resource.getContent())
                .fileUrl(resource.getFileUrl())
                .coverImageUrl(resource.getCoverImageUrl())
                .publishDate(resource.getPublishDate())
                .author(resource.getAuthor())
                .displayOrder(resource.getDisplayOrder())
                .active(resource.getActive())
                .createdAt(resource.getCreatedAt())
                .updatedAt(resource.getUpdatedAt())
                .build();
    }
}