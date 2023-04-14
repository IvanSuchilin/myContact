package com.example.myContact.mappers;

import com.example.myContact.dto.contactDto.ContactCreatingDto;
import com.example.myContact.dto.contactDto.ContactDto;
import com.example.myContact.dto.contactDto.ContactDtoUpd;
import com.example.myContact.dto.groupDto.GroupCreatingDto;
import com.example.myContact.dto.groupDto.GroupDto;
import com.example.myContact.dto.groupDto.GroupDtoUpd;
import com.example.myContact.model.Contact;
import com.example.myContact.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);
    Group toGroup(GroupCreatingDto groupCreatingDto);
    GroupDto toGroupDto(Group stored);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    void updateGroup(GroupDtoUpd groupDtoUpd,@MappingTarget Group stored);

    @Mapping(target = "id", ignore = true)
    Contact toContact(ContactCreatingDto contactCreatingDto);

    @Mapping(target = "group", source = "group.id")
    ContactDto toContactDto(Contact contact);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "religion", ignore = true)
    @Mapping(target = "group.id", ignore = true)
    void updateContact(ContactDtoUpd contactDtoUpd,@MappingTarget Contact stored);
}
