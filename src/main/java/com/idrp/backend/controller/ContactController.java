package com.idrp.backend.controller;

import com.idrp.backend.dto.common.ApiResponse;
import com.idrp.backend.dto.contact.ContactRequestDto;
import com.idrp.backend.dto.contact.ContactResponseDto;
import com.idrp.backend.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<ApiResponse<ContactResponseDto>> createContact(
            @Valid @RequestBody ContactRequestDto requestDto
    ) {
        ContactResponseDto createdContact = contactService.createContact(requestDto);

        return new ResponseEntity<>(
                ApiResponse.<ContactResponseDto>builder()
                        .success(true)
                        .message("Contact created successfully")
                        .data(createdContact)
                        .build(),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ContactResponseDto>>> getAllContacts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ContactResponseDto> contacts = contactService.getAllContacts(page, size);

        return ResponseEntity.ok(
                ApiResponse.<Page<ContactResponseDto>>builder()
                        .success(true)
                        .message("Contacts fetched successfully")
                        .data(contacts)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ContactResponseDto>> getContactById(@PathVariable Long id) {
        ContactResponseDto contact = contactService.getContactById(id);

        return ResponseEntity.ok(
                ApiResponse.<ContactResponseDto>builder()
                        .success(true)
                        .message("Contact fetched successfully")
                        .data(contact)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ContactResponseDto>> updateContact(
            @PathVariable Long id,
            @Valid @RequestBody ContactRequestDto requestDto
    ) {
        ContactResponseDto updatedContact = contactService.updateContact(id, requestDto);

        return ResponseEntity.ok(
                ApiResponse.<ContactResponseDto>builder()
                        .success(true)
                        .message("Contact updated successfully")
                        .data(updatedContact)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);

        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .message("Contact deleted successfully")
                        .data(null)
                        .build()
        );
    }
}