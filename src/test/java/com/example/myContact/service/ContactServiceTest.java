package com.example.myContact.service;

import com.example.myContact.dto.contactDto.ContactCreatingDto;
import com.example.myContact.dto.contactDto.ContactDto;
import com.example.myContact.dto.contactDto.ContactDtoUpd;
import com.example.myContact.dto.groupDto.GroupCreatingDto;
import com.example.myContact.exceptions.NotFoundException;
import com.example.myContact.exceptions.RequestValidationException;
import com.example.myContact.model.Contact;
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
class ContactServiceTest {
    private static GroupCreatingDto group1;
    private static GroupCreatingDto group2;
    private static ContactCreatingDto contact1;
    private static ContactCreatingDto contact2;

    private static ContactCreatingDto contact3;
    @Autowired
    private GroupService groupService;
    @Autowired
    private ContactService contactService;

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

        contact1 = new ContactCreatingDto("Name contact1", "Company contact 1", "q@mail", Contact.Religion.BUDDHISM);
        contact2 = new ContactCreatingDto("Name contact2", "Company contact 2", "q2@mail", Contact.Religion.HINDUISM);
        contact3 = new ContactCreatingDto("Name contact3", "Company contact 3", "q3@mail", Contact.Religion.BUDDHISM);
    }

    @Test
    void createContactWrongGroup() {
        NotFoundException thrown = Assertions.assertThrows(NotFoundException.class,
                () -> contactService.createContact(1L, contact1));
        Assertions.assertEquals("Запрашиваемый объект не найден или не доступен", thrown.getReason());
        Assertions.assertEquals(NOT_FOUND, thrown.getStatus());
    }

    @Test
    void createContactEmptyName() {
        groupService.createGroup(group1);
        contact3.setName("");
        RequestValidationException thrown = Assertions.assertThrows(RequestValidationException.class,
                () -> contactService.createContact(1L, contact3));
        Assertions.assertEquals("Имя контакта пусто", thrown.getReason());
        Assertions.assertEquals(BAD_REQUEST, thrown.getStatus());
    }

    @Test
    void createContact() {
        groupService.createGroup(group1);
        ContactDto stored = (ContactDto) contactService.createContact(1L, contact1);

        assertEquals(1L, stored.getGroup());
        assertEquals("Name contact1", stored.getName());
        assertEquals("q@mail", stored.getEmail());
    }

    @Test
    void deleteContactWrong() {
        NotFoundException thrown = Assertions.assertThrows(NotFoundException.class,
                () -> contactService.deleteContact(1L));
        Assertions.assertEquals("Запрашиваемый объект не найден или не доступен", thrown.getReason());
        Assertions.assertEquals(NOT_FOUND, thrown.getStatus());
    }

    @Test
    void getContactById() {
        groupService.createGroup(group2);
        contactService.createContact(1L, contact2);
        ContactDto stored = (ContactDto) contactService.getContactById(1L);

        assertEquals(1L, stored.getGroup());
        assertEquals("Name contact2", stored.getName());
        assertEquals("q2@mail", stored.getEmail());
    }

    @Test
    void getContactByWrongId() {
        NotFoundException thrown = Assertions.assertThrows(NotFoundException.class,
                () -> contactService.getContactById(10L));
        Assertions.assertEquals("Запрашиваемый объект не найден или не доступен", thrown.getReason());
        Assertions.assertEquals(NOT_FOUND, thrown.getStatus());
    }


    @Test
    void getContacts() {
        groupService.createGroup(group1);
        groupService.createGroup(group2);
        contactService.createContact(1L, contact1);
        contactService.createContact(2L, contact2);
        List<Contact> contacts = (List<Contact>) contactService.getContacts(0, 10);

        assertEquals(2, contacts.size());
    }

    @Test
    void update() {
        groupService.createGroup(group1);
        contactService.createContact(1L, contact1);
        ContactDtoUpd contactDtoUpd = new ContactDtoUpd();
        contactDtoUpd.setName("Upd");
        ContactDto stored = (ContactDto) contactService.update(1L, contactDtoUpd);

        assertEquals(1L, stored.getGroup());
        assertEquals("Upd", stored.getName());
        assertEquals("q@mail", stored.getEmail());
    }

    @Test
    void updateWrongId(){
        NotFoundException thrown = Assertions.assertThrows(NotFoundException.class,
                () -> contactService.update(10L, new ContactDtoUpd()));
        Assertions.assertEquals("Запрашиваемый объект не найден или не доступен", thrown.getReason());
        Assertions.assertEquals(NOT_FOUND, thrown.getStatus());
    }
    @Test
    void updateWrongEmail(){
        groupService.createGroup(group1);
        contactService.createContact(1L, contact1);
        ContactDtoUpd contactDtoUpd = new ContactDtoUpd();
        contactDtoUpd.setEmail("mail");

        RequestValidationException thrown = Assertions.assertThrows(RequestValidationException.class,
                () -> contactService.update(1L,contactDtoUpd));
        Assertions.assertEquals("Неверный email", thrown.getReason());
        Assertions.assertEquals(BAD_REQUEST, thrown.getStatus());
    }
}