package com.example.myContact.service;

import com.example.myContact.dto.contactDto.ContactCreatingDto;
import com.example.myContact.exceptions.NotFoundException;
import com.example.myContact.mappers.ContactMapper;
import com.example.myContact.model.Contact;
import com.example.myContact.model.Group;
import com.example.myContact.repository.ContactRepository;
import com.example.myContact.repository.GroupRepository;
import com.example.myContact.validation.DtoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final DtoValidator validator;
    private final GroupRepository groupRepository;

    public Object createContact(Long groupId, ContactCreatingDto newContact) {
        Group stored = groupRepository.findById(groupId).orElseThrow(() ->
                new NotFoundException("Группа с id" + groupId + "  найдена", "Запрашиваемый объект не найден или не доступен",
                        LocalDateTime.now()));
        Contact contact = ContactMapper.INSTANCE.toContact(newContact);
        contact.setGroup(stored);
        return ContactMapper.INSTANCE.toContactDto(contactRepository.save(contact));
    }
}
