package com.example.myContact.validation;

import com.example.myContact.dto.groupDto.GroupCreatingDto;
import com.example.myContact.exceptions.RequestValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class DtoValidator {
    LocalDateTime time = LocalDateTime.now();

    public void validateGroupCreatingDto(GroupCreatingDto groupCreatingDto) {
        if (StringUtils.isBlank(groupCreatingDto.getDescription())) {
            throw new RequestValidationException(
                    "Не указано описание группы",
                    "Описание группы пусто",
                    LocalDateTime.now()
            );
        }
        if (StringUtils.isBlank(groupCreatingDto.getName())) {
            throw new RequestValidationException(
                    "Не указано имя группы",
                    "имя группы пусто",
                    LocalDateTime.now()
            );
        }
    }
}
