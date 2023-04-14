package com.example.myContact.dto.groupDto;

import com.example.myContact.model.Contact;
import com.example.myContact.model.Group;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDtoUpd {
        private String name;
        private String description;
        private Group.Category category;
}
