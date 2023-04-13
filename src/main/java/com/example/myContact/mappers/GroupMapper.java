package com.example.myContact.mappers;

import com.example.myContact.dto.GroupCreatingDto;
import com.example.myContact.model.Group;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        //uses = UserMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    //@Mapping(target = "category.id", source = "category")
    Group toGroup(GroupCreatingDto groupCreatingDto);
}
