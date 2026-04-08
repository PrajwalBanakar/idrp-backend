package com.idrp.backend.service.impl;

import com.idrp.backend.dto.contact.ContactRequestDto;
import com.idrp.backend.dto.contact.ContactResponseDto;
import com.idrp.backend.entity.Contact;
import com.idrp.backend.exception.ResourceNotFoundException;
import com.idrp.backend.repository.ContactRepository;
import com.idrp.backend.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public ContactResponseDto createContact(ContactRequestDto requestDto) {
        Contact contact = mapToEntity(requestDto);
        Contact savedContact = contactRepository.save(contact);
        return mapToResponseDto(savedContact);
    }

    @Override
    public Page<ContactResponseDto> getAllContacts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));

        return contactRepository.findAll(pageable)
                .map(this::mapToResponseDto);
    }

    @Override
    public ContactResponseDto getContactById(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + id));

        return mapToResponseDto(contact);
    }

    @Override
    public ContactResponseDto updateContact(Long id, ContactRequestDto requestDto) {
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + id));

        existingContact.setFullName(requestDto.getFullName());
        existingContact.setEmail(requestDto.getEmail());
        existingContact.setPhone(requestDto.getPhone());
        existingContact.setSubject(requestDto.getSubject());
        existingContact.setMessage(requestDto.getMessage());

        Contact updatedContact = contactRepository.save(existingContact);
        return mapToResponseDto(updatedContact);
    }

    @Override
    public void deleteContact(Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id: " + id));

        contactRepository.delete(contact);
    }

    private Contact mapToEntity(ContactRequestDto dto) {
        return Contact.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .subject(dto.getSubject())
                .message(dto.getMessage())
                .build();
    }

    private ContactResponseDto mapToResponseDto(Contact contact) {
        return ContactResponseDto.builder()
                .id(contact.getId())
                .fullName(contact.getFullName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .subject(contact.getSubject())
                .message(contact.getMessage())
                .createdAt(contact.getCreatedAt())
                .updatedAt(contact.getUpdatedAt())
                .build();
    }
}