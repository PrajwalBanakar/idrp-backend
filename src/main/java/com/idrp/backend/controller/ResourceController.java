package com.idrp.backend.controller;

import com.idrp.backend.dto.common.ApiResponse;
import com.idrp.backend.dto.resource.ResourceRequestDto;
import com.idrp.backend.dto.resource.ResourceResponseDto;
import com.idrp.backend.service.ResourceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resources")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ResourceController {

    private final ResourceService resourceService;

    @PostMapping
    public ResponseEntity<ApiResponse<ResourceResponseDto>> createResource(
            @Valid @RequestBody ResourceRequestDto requestDto
    ) {
        ResourceResponseDto resource = resourceService.createResource(requestDto);

        return new ResponseEntity<>(
                ApiResponse.<ResourceResponseDto>builder()
                        .success(true)
                        .message("Resource created successfully")
                        .data(resource)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ResourceResponseDto>>> getAllResources(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ResourceResponseDto> resources = resourceService.getAllResources(page, size);

        return ResponseEntity.ok(
                ApiResponse.<Page<ResourceResponseDto>>builder()
                        .success(true)
                        .message("Resources fetched successfully")
                        .data(resources)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ResourceResponseDto>> getResourceById(@PathVariable Long id) {
        ResourceResponseDto resource = resourceService.getResourceById(id);

        return ResponseEntity.ok(
                ApiResponse.<ResourceResponseDto>builder()
                        .success(true)
                        .message("Resource fetched successfully")
                        .data(resource)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ResourceResponseDto>> updateResource(
            @PathVariable Long id,
            @Valid @RequestBody ResourceRequestDto requestDto
    ) {
        ResourceResponseDto resource = resourceService.updateResource(id, requestDto);

        return ResponseEntity.ok(
                ApiResponse.<ResourceResponseDto>builder()
                        .success(true)
                        .message("Resource updated successfully")
                        .data(resource)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteResource(@PathVariable Long id) {
        resourceService.deleteResource(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Resource deleted successfully")
                        .data(null)
                        .build()
        );
    }
}