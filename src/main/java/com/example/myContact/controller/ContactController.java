package com.example.myContact.controller;

import com.example.myContact.dto.contactDto.ContactDtoUpd;
import com.example.myContact.dto.groupDto.GroupDtoUpd;
import com.example.myContact.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping
public class ContactController {
    private final ContactService contactService;

    @DeleteMapping("/contacts/{contactId}")
    public ResponseEntity<Object> deleteContact(@Positive @PathVariable Long contactId) {
        log.info("Удаление контакта id {}", contactId);
        contactService.deleteContact(contactId);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/contacts/{contactId}")
    public ResponseEntity<Object> getGroupById(@Positive @PathVariable Long contactId) {
        return new ResponseEntity<>(contactService.getContactById(contactId), HttpStatus.OK);
    }

   @GetMapping("/contacts")
    public ResponseEntity<Object> getEvents(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                            @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех контактов");
        return new ResponseEntity<>(contactService.getContacts(from, size),
                HttpStatus.OK);
    }

    @PatchMapping("/contacts/{contactId}")
    public ResponseEntity<Object> patch(@Positive @PathVariable Long contactId, @RequestBody ContactDtoUpd
            contactDtoUpd) {
        log.info("Обновление данных контакта {}", contactId);
        return new ResponseEntity<>(contactService.update(contactId, contactDtoUpd), HttpStatus.CREATED);
    }
}
