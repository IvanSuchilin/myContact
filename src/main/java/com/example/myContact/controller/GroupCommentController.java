package com.example.myContact.controller;

import com.example.myContact.dto.contactDto.ContactCreatingDto;
import com.example.myContact.dto.groupDto.GroupCreatingDto;
import com.example.myContact.dto.groupDto.GroupDtoUpd;
import com.example.myContact.service.ContactService;
import com.example.myContact.service.GroupService;
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
public class GroupCommentController {
    private final GroupService groupService;
    private final ContactService contactService;

    @PostMapping("/groups")
    public ResponseEntity<Object> createGroup(@RequestBody GroupCreatingDto newGroup) {
        log.info("Создание группы {}", newGroup.getName());
        return new ResponseEntity<>(groupService.createGroup(newGroup), HttpStatus.CREATED);
    }

    @DeleteMapping("/groups/{groupId}")
    public ResponseEntity<Object> deleteGroup(@Positive @PathVariable("groupId") Long id) {
        log.info("Удаление группы id {}", id);
        groupService.deleteGroup(id);
        return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<Object> getGroupById(@Positive @PathVariable Long groupId) {
        return new ResponseEntity<>(groupService.getGroupById(groupId), HttpStatus.OK);
    }

    @GetMapping("/groups")
    public ResponseEntity<Object> getEvents(@PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                            @Positive @RequestParam(defaultValue = "10") Integer size) {
        log.info("Получение всех групп");
        return new ResponseEntity<>(groupService.getGroups(from, size),
                HttpStatus.OK);
    }

    @PatchMapping("/groups/{groupId}")
    public ResponseEntity<Object> patch(@Positive @PathVariable("groupId") Long groupId, @RequestBody GroupDtoUpd
            groupDtoUpd) {
        log.info("Обновление данных вещи {}", groupId);
        return new ResponseEntity<>(groupService.update(groupId, groupDtoUpd), HttpStatus.CREATED);
    }

    @PostMapping("/groups/{groupId}/contact")
    public ResponseEntity<Object> createGroup(@Positive @PathVariable Long groupId, @RequestBody ContactCreatingDto
            newContact) {
        log.info("Создание контакта группы {}", groupId);
        return new ResponseEntity<>(contactService.createContact(groupId, newContact), HttpStatus.CREATED);
    }
}
