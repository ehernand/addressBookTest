package com.code.challenge.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String nickName;

    @NotNull
    private String email;

    private LocalDate dateOfBirth;

    private Boolean active;

    @Enumerated(EnumType.STRING)
    private ContactType contactType = ContactType.Personal; //by default all are type personal :)

    @ManyToMany
    @JoinTable(name = "contact_event",
            joinColumns = @JoinColumn(name="contact_id"),
                inverseJoinColumns = @JoinColumn(name="event_id"))
    private Set<Event> events;
}
