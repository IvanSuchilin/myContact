package com.example.myContact.dto.contactDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDtoUpd {
    private String name;
    private String company;
    private String email;
    private Long groupId;
}
