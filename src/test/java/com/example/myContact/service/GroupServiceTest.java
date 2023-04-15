package com.example.myContact.service;

import com.example.myContact.dto.groupDto.GroupCreatingDto;
import com.example.myContact.dto.groupDto.GroupDto;
import com.example.myContact.dto.groupDto.GroupDtoUpd;
import com.example.myContact.exceptions.NotFoundException;
import com.example.myContact.exceptions.RequestValidationException;
import com.example.myContact.model.Group;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Sql(scripts = {"file:src/test/java/resources/scripts/schemaTest.sql"})
class GroupServiceTest {
    private static GroupCreatingDto group1;
    private static GroupCreatingDto group2;
    @Autowired
    private GroupService groupService;

    @BeforeAll
    public static void setup() {
        group1 = new GroupCreatingDto();
        group1.setCategory(Group.Category.FAMILY);
        group1.setDescription("group1 description");
        group1.setName("group1 name");

        group2 = new GroupCreatingDto();
        group2.setCategory(Group.Category.JOB);
        group2.setDescription("group2 description");
        group2.setName("group2 name");
    }

    @Test
    void createGroupEmptyName() {
        group2.setName("");
        RequestValidationException thrown = Assertions.assertThrows(RequestValidationException.class,
                () -> groupService.createGroup(group2));
        Assertions.assertEquals("имя группы пусто", thrown.getReason());
        Assertions.assertEquals(BAD_REQUEST, thrown.getStatus());
    }

    @Test
    void createGroup() {
        Group stored = (Group) groupService.createGroup(group1);

        assertEquals(Group.Category.FAMILY, stored.getCategory());
        assertEquals("group1 name", stored.getName());
    }

    @Test
    void deleteGroupWrongId() {
        NotFoundException thrown = Assertions.assertThrows(NotFoundException.class,
                () -> groupService.deleteGroup(1L));
        Assertions.assertEquals("Запрашиваемый объект не найден или не доступен", thrown.getReason());
        Assertions.assertEquals(NOT_FOUND, thrown.getStatus());
    }

    @Test
    void getGroupWrongId() {
        NotFoundException thrown = Assertions.assertThrows(NotFoundException.class,
                () -> groupService.getGroupById(10L));
        Assertions.assertEquals("Запрашиваемый объект не найден или не доступен", thrown.getReason());
        Assertions.assertEquals(NOT_FOUND, thrown.getStatus());
    }

    @Test
    void getGroupById() {
        groupService.createGroup(group1);
        GroupDto stored = (GroupDto) groupService.getGroupById(1L);

        assertEquals(Group.Category.FAMILY, stored.getCategory());
        assertEquals("group1 name", stored.getName());
    }

    @Test
    void getGroups() {
        groupService.createGroup(group1);
        List<Group> groups = (List<Group>) groupService.getGroups(0, 10);

        assertEquals(1, groups.size());
    }

    @Test
    void updateWrongId() {
        NotFoundException thrown = Assertions.assertThrows(NotFoundException.class,
                () -> groupService.update(10L, new GroupDtoUpd()));
        Assertions.assertEquals("Запрашиваемый объект не найден или не доступен", thrown.getReason());
        Assertions.assertEquals(NOT_FOUND, thrown.getStatus());
    }

    @Test
    void update() {
        groupService.createGroup(group1);
        GroupDtoUpd groupDtoUpd = new GroupDtoUpd();
        groupDtoUpd.setName("NewGroupName");
        GroupDto stored = (GroupDto) groupService.update(1L, groupDtoUpd);

        assertEquals("NewGroupName", stored.getName());
    }
}