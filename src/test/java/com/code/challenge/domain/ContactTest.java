package com.code.challenge.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    Contact contactTest;

    @BeforeEach
    public void setUp() {
        contactTest = new Contact();
    }

    @Test
    void getId() {
        //given
        Long idTest = 1l;

        //when
        contactTest.setId(idTest);

        //then
        assertEquals(idTest, contactTest.getId());
    }

    @Test
    void getFirstName() {
        //given
        String firstNameTest = "test";

        //when
        contactTest.setFirstName(firstNameTest);

        //then
        assertEquals(firstNameTest, contactTest.getFirstName());
    }

    @Test
    void getLastName() {
        //given
        String lastNameTest = "test";

        //when
        contactTest.setLastName(lastNameTest);

        //then
        assertEquals(lastNameTest, contactTest.getLastName());
    }

    @Test
    void getNickName() {
        //given
        String nickNameTest = "test";

        //when
        contactTest.setNickName(nickNameTest);

        //then
        assertEquals(nickNameTest, contactTest.getNickName());
    }

    @Test
    void getEmail() {
        //given
        String emailTest = "test@test.com";

        //when
        contactTest.setFirstName(emailTest);

        //then
        assertEquals(emailTest, contactTest.getFirstName());
    }

    //TODO validate email format when adding email pattern :)

    @Test
    void getDateOfBirth() {
        //given
        LocalDateTime bday = LocalDateTime.now();

        // is a baby!

        //when
        contactTest.setDateOfBirth(bday);

        //then
        assertEquals(bday, contactTest.getDateOfBirth());
    }

    @Test
    void getActive() {
        //given
        Boolean isActive = false;

        //when
        contactTest.setActive(isActive);

        //then
        assertEquals(isActive, contactTest.getActive());
    }

    @Test
    void getContactType() {
        //given
        ContactType cType = ContactType.Other;

        //when
        contactTest.setContactType(cType);

        //then
        assertEquals(cType, contactTest.getContactType());
    }


    @Test
    void getContactTypeAsDefault() {
        //then
        assertEquals(ContactType.Personal, contactTest.getContactType());
    }

    @Test
    void getEventsEmptyByDefault() {
        //given
        Set<Event> events = new HashSet<>();

        //when
        contactTest.setEvents(events);

        //then
        assertEquals(events.isEmpty(), contactTest.getEvents().isEmpty());
    }

    @Test
    void getEvents() {
        //given
        Event testEvent = new Event();
        testEvent.setId(1l);
        Set<Event> events = new HashSet<>();
        events.add(testEvent);

        //when
        contactTest.setEvents(events);

        //then
        assertEquals(events, contactTest.getEvents());
    }
}