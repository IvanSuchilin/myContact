package com.example.myContact.validation;

import com.example.myContact.dto.contactDto.ContactCreatingDto;
import com.example.myContact.dto.contactDto.ContactDtoUpd;
import com.example.myContact.dto.groupDto.GroupCreatingDto;
import com.example.myContact.dto.groupDto.GroupDtoUpd;
import com.example.myContact.exceptions.RequestValidationException;
import com.example.myContact.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class DtoValidator {
    private final ContactRepository contactRepository;
    LocalDateTime time = LocalDateTime.now();

    public void validateGroupCreatingDto(GroupCreatingDto groupCreatingDto) {
        if (StringUtils.isBlank(groupCreatingDto.getDescription())) {
            log.warn("Описание группы пусто");
            throw new RequestValidationException(
                    "Не указано описание группы",
                    "Описание группы пусто",
                    time
            );
        }
        if (StringUtils.isBlank(groupCreatingDto.getName())) {
            log.warn("Имя группы пусто");
            throw new RequestValidationException(
                    "Не указано имя группы",
                    "имя группы пусто",
                    time
            );
        }
    }

    public void validateGroupUpdDto(GroupDtoUpd groupDtoUpd) {
        if (groupDtoUpd.getDescription() != null) {
            if (StringUtils.isBlank(groupDtoUpd.getDescription())) {
                log.warn("Описание группы пусто");
                throw new RequestValidationException(
                        "Не указано описание группы",
                        "Описание группы пусто",
                        time
                );
            }
        }
        if (groupDtoUpd.getName() != null) {
            if (StringUtils.isBlank(groupDtoUpd.getName())) {
                log.warn("Имя группы пусто");
                throw new RequestValidationException(
                        "Не указано имя группы",
                        "имя группы пусто",
                        time
                );
            }
        }
    }

    public void validateContactCreatingDto(ContactCreatingDto contactCreatingDto) {
        if (StringUtils.isBlank(contactCreatingDto.getName())) {
            log.warn("Имя контакта пусто");
            throw new RequestValidationException(
                    "Не указано имя крнтакта",
                    "Имя контакта пусто",
                    time
            );
        }
        if (StringUtils.isBlank(contactCreatingDto.getCompany())) {
            log.warn("Название компании пусто");
            throw new RequestValidationException(
                    "Не указана компания контакта",
                    "Не указана компания",
                    time
            );
        }
        if (StringUtils.isBlank(contactCreatingDto.getEmail()) || !contactCreatingDto.getEmail().contains("@")) {
            log.warn("Адрес электронной почты не может быть пустым и должен содержать символ @");
            throw new RequestValidationException(
                    "Адрес электронной почты не может быть пустым и должен содержать символ @",
                    "Неверный email",
                    time
            );
        }
        if (contactRepository.findAll()
                .stream()
                .anyMatch(u -> u.getEmail().equals(contactCreatingDto.getEmail()))) {
            throw new RequestValidationException(
                    "Пользователь с такой почтой уже существует",
                    "Неверный email",
                    time
            );
        }
    }

    public void validateContactUpdDto(ContactDtoUpd contactDtoUpd) {
        if (contactDtoUpd.getName() != null) {
            if (StringUtils.isBlank(contactDtoUpd.getName())) {
                log.warn("Имя контакта пусто");
                throw new RequestValidationException(
                        "Не указано имя крнтакта",
                        "Имя контакта пусто",
                        time
                );
            }
        }
        if (contactDtoUpd.getCompany() != null) {
            if (StringUtils.isBlank(contactDtoUpd.getCompany())) {
                log.warn("Название компании пусто");
                throw new RequestValidationException(
                        "Не указана компания контакта",
                        "Не указана компания",
                        time
                );
            }
        }
        if (contactDtoUpd.getEmail() != null) {
            if (StringUtils.isBlank(contactDtoUpd.getEmail()) || !contactDtoUpd.getEmail().contains("@")) {
                log.warn("Адрес электронной почты не может быть пустым и должен содержать символ @");
                throw new RequestValidationException(
                        "Адрес электронной почты не может быть пустым и должен содержать символ @",
                        "Неверный email",
                        time
                );
            }
            if (contactRepository.findAll()
                    .stream()
                    .anyMatch(u -> u.getEmail().equals(contactDtoUpd.getEmail()))) {
                throw new RequestValidationException(
                        "Пользователь с такой почтой уже существует",
                        "Неверный email",
                        time
                );
            }
        }
    }
}
