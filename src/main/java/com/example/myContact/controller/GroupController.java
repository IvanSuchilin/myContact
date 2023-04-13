package com.example.myContact.controller;

import com.example.myContact.dto.GroupCreatingDto;
import com.example.myContact.service.GroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/groups")
    public ResponseEntity<Object> createGroup(@RequestBody GroupCreatingDto newGroup) {
        log.info("Создание группы {}", newGroup.getName());
        return new ResponseEntity<>(groupService.createGroup(newGroup), HttpStatus.CREATED);
    }
}
