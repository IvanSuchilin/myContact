package com.example.myContact.dto.contactDto;

import com.example.myContact.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {
    private String name;
    private Long group;
    private String company;
    private String email;
    private Contact.Religion religion;
}
