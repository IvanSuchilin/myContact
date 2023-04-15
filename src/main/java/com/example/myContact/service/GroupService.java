package com.example.myContact.service;

import com.example.myContact.dto.groupDto.GroupCreatingDto;
import com.example.myContact.dto.groupDto.GroupDtoUpd;
import com.example.myContact.exceptions.NotFoundException;
import com.example.myContact.mappers.GroupMapper;
import com.example.myContact.model.Group;
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
public class GroupService {
    private final GroupRepository groupRepository;
    private final DtoValidator validator;

    public Object createGroup(GroupCreatingDto newGroup) {
        validator.validateGroupCreatingDto(newGroup);
        log.info("Создание новой группы контактов {}", newGroup.getName());
        Group saveGroup = GroupMapper.INSTANCE.toGroup(newGroup);
        saveGroup.setCreatedOn(LocalDateTime.now());
        return groupRepository.save(saveGroup);
    }

    public void deleteGroup(Long groupId) {
        log.info("Удаление группы {}", groupId);
        groupRepository.findById(groupId).orElseThrow(() ->
                new NotFoundException("Группа с id" + groupId + "не найдена", "Запрашиваемый объект не найден или не доступен",
                        LocalDateTime.now()));
        groupRepository.deleteById(groupId);
    }

    public Object getGroupById(Long id) {
        log.info("Получение информации о группе id={}", id);
        Group stored = groupRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Группа с id" + id + "не найдена", "Запрашиваемый объект не найден или не доступен",
                        LocalDateTime.now()));
        return GroupMapper.INSTANCE.toGroupDto(stored);
    }

    public Object getGroups(Integer from, Integer size) {
        log.info("Получение информации о всех группах");
        Pageable pageable = PageRequest.of(from / size, size);
        return groupRepository.findAll(pageable).getContent()
                .stream()
                .map(GroupMapper.INSTANCE::toGroupDto)
                .collect(Collectors.toList());
    }

    public Object update(Long groupId, GroupDtoUpd groupDtoUpd) {
        log.info("Обновление информации о группе id={}", groupId);
        Group stored = groupRepository.findById(groupId).orElseThrow(() ->
                new NotFoundException("Группа с id" + groupId + "не найдена", "Запрашиваемый объект не найден или не доступен",
                        LocalDateTime.now()));
        validator.validateGroupUpdDto(groupDtoUpd);
        GroupMapper.INSTANCE.updateGroup(groupDtoUpd, stored);
        return GroupMapper.INSTANCE.toGroupDto(groupRepository.save(stored));
    }
}
