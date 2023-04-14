package com.example.myContact.mappers;

import com.example.myContact.dto.contactDto.ContactCreatingDto;
import com.example.myContact.dto.contactDto.ContactDto;
import com.example.myContact.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        uses = GroupMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    @Mapping(target = "id", ignore = true)
    Contact toContact(ContactCreatingDto contactCreatingDto);

    @Mapping(target = "group", source = "group.id")
    ContactDto toContactDto(Contact contact);
}
