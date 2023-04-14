package com.example.myContact.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "public", name = "contacts")
public class Contact {
    @Positive
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id", nullable = false)
    private Long id;
    @Column(name = "contact_name", nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;
    @Column(name = "contact_company", nullable = false)
    private String company;
    @Column(name = "contact_email", nullable = false, unique = true)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "contact_religion", nullable = false)
    private Contact.Religion religion;

    public enum Religion {
        CHRISTIANITY,
        ISLAM,
        HINDUISM,
        BUDDHISM,
        ANOTHER
    }
}
