package com.example.myContact.service;

import com.example.myContact.dto.GroupCreatingDto;
import com.example.myContact.mappers.GroupMapper;
import com.example.myContact.model.Group;
import com.example.myContact.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    //private final DtoValidator validator;
    DateTimeFormatter returnedTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public Object createGroup(GroupCreatingDto newGroup) {
        //validator.validateNewEventDto(newGroup);
        log.info("Создание новой группы контактов {}", newGroup.getName());
        Group saveGroup = GroupMapper.INSTANCE.toGroup(newGroup);
        saveGroup.setCreatedOn(LocalDateTime.now());
        return groupRepository.save(saveGroup);
    }
}
