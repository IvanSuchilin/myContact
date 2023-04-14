package com.example.myContact.dto;

import com.example.myContact.model.Contact;
import com.example.myContact.model.Group;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {
    private String name;
    private String description;
    private Group.Category category;
    private List<Contact> contacts;
}
