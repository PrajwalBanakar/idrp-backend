package com.idrp.backend.controller;

import com.idrp.backend.dto.common.ApiResponse;
import com.idrp.backend.dto.partner.PartnerRequestDto;
import com.idrp.backend.dto.partner.PartnerResponseDto;
import com.idrp.backend.service.PartnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partners")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PartnerController {

    private final PartnerService partnerService;

    @PostMapping
    public ResponseEntity<ApiResponse<PartnerResponseDto>> createPartner(
            @Valid @RequestBody PartnerRequestDto requestDto
    ) {
        PartnerResponseDto partner = partnerService.createPartner(requestDto);

        return new ResponseEntity<>(
                ApiResponse.<PartnerResponseDto>builder()
                        .success(true)
                        .message("Partner created successfully")
                        .data(partner)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<PartnerResponseDto>>> getAllPartners(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<PartnerResponseDto> partners = partnerService.getAllPartners(page, size);

        return ResponseEntity.ok(
                ApiResponse.<Page<PartnerResponseDto>>builder()
                        .success(true)
                        .message("Partners fetched successfully")
                        .data(partners)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PartnerResponseDto>> getPartnerById(@PathVariable Long id) {
        PartnerResponseDto partner = partnerService.getPartnerById(id);

        return ResponseEntity.ok(
                ApiResponse.<PartnerResponseDto>builder()
                        .success(true)
                        .message("Partner fetched successfully")
                        .data(partner)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PartnerResponseDto>> updatePartner(
            @PathVariable Long id,
            @Valid @RequestBody PartnerRequestDto requestDto
    ) {
        PartnerResponseDto partner = partnerService.updatePartner(id, requestDto);

        return ResponseEntity.ok(
                ApiResponse.<PartnerResponseDto>builder()
                        .success(true)
                        .message("Partner updated successfully")
                        .data(partner)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePartner(@PathVariable Long id) {
        partnerService.deletePartner(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Partner deleted successfully")
                        .data(null)
                        .build()
        );
    }
}