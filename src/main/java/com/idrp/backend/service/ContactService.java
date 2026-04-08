package com.idrp.backend.service;

import com.idrp.backend.dto.contact.ContactRequestDto;
import com.idrp.backend.dto.contact.ContactResponseDto;
import org.springframework.data.domain.Page;

public interface ContactService {

    ContactResponseDto createContact(ContactRequestDto requestDto);

    Page<ContactResponseDto> getAllContacts(int page, int size);

    ContactResponseDto getContactById(Long id);

    ContactResponseDto updateContact(Long id, ContactRequestDto requestDto);

    void deleteContact(Long id);
}