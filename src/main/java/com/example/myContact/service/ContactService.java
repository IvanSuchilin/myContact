package com.example.myContact.service;

import com.example.myContact.dto.contactDto.ContactCreatingDto;
import com.example.myContact.dto.contactDto.ContactDtoUpd;
import com.example.myContact.exceptions.NotFoundException;
import com.example.myContact.mappers.GroupMapper;
import com.example.myContact.model.Contact;
import com.example.myContact.model.Group;
import com.example.myContact.repository.ContactRepository;
import com.example.myContact.repository.GroupRepository;
import com.example.myContact.validation.DtoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final DtoValidator validator;
    private final GroupRepository groupRepository;

    public Object createContact(Long groupId, ContactCreatingDto newContact) {
        validator.validateContactCreatingDto(newContact);
        log.info("Создание нового контакта {}", newContact.getName());
        Group stored = groupRepository.findById(groupId).orElseThrow(() ->
                new NotFoundException("Группа с id" + groupId + "  найдена",
                        "Запрашиваемый объект не найден или не доступен",
                        LocalDateTime.now()));
        Contact contact = GroupMapper.INSTANCE.toContact(newContact);
        contact.setGroup(stored);
        return GroupMapper.INSTANCE.toContactDto(contactRepository.save(contact));
    }

    public void deleteContact(Long contactId) {
        log.info("Удаление котакта {}", contactId);
        contactRepository.findById(contactId).orElseThrow(() ->
                new NotFoundException("Контакт с id" + contactId + " не найден",
                        "Запрашиваемый объект не найден или не доступен",
                        LocalDateTime.now()));
        contactRepository.deleteById(contactId);
    }

    public Object getContactById(Long contactId) {
        log.info("Получение информации о контакте id={}", contactId);
        Contact stored = contactRepository.findById(contactId).orElseThrow(() ->
                new NotFoundException("Контакт с id" + contactId + " не найден",
                        "Запрашиваемый объект не найден или не доступен",
                        LocalDateTime.now()));
        return GroupMapper.INSTANCE.toContactDto(stored);
    }

    public Object getContacts(Integer from, Integer size) {
        log.info("Получение информации о всех контактах");
        Pageable pageable = PageRequest.of(from / size, size);
        return contactRepository.findAll(pageable).getContent()
                .stream()
                .map(GroupMapper.INSTANCE::toContactDto)
                .collect(Collectors.toList());
    }

    public Object update(Long contactId, ContactDtoUpd contactDtoUpd) {
        log.info("Обновление информации о контакте id={}", contactId);
        Contact stored = contactRepository.findById(contactId).orElseThrow(() ->
                new NotFoundException("Контакт с id" + contactId + " не найден",
                        "Запрашиваемый объект не найден или не доступен",
                        LocalDateTime.now()));
        validator.validateContactUpdDto(contactDtoUpd);
        GroupMapper.INSTANCE.updateContact(contactDtoUpd, stored);
        return GroupMapper.INSTANCE.toContactDto(contactRepository.save(stored));
    }
}
