package com.code.challenge.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    Event eventTest;

    @BeforeEach
    public void setUp() {
        eventTest = new Event();
    }

    @Test
    void getId() {
        //given
        Long idTest = 1l;

        //when
        eventTest.setId(idTest);

        //then
        assertEquals(idTest, eventTest.getId());
    }

    @Test
    void getStartDateTime() {
        //given
        LocalDateTime testDate = LocalDateTime.now();

        //when
        eventTest.setStartDateTime(testDate);

        //then
        assertEquals(testDate, eventTest.getStartDateTime());
    }

    @Test
    void getEndDateTime() {
        //given
        LocalDateTime testDate = LocalDateTime.now();

        //when
        eventTest.setEndDateTime(testDate);

        //then
        assertEquals(testDate, eventTest.getEndDateTime());
    }

    @Test
    void getLocation() {
        //given
        String locationTest = "test";

        //when
        eventTest.setLocation(locationTest);

        //then
        assertEquals(locationTest, eventTest.getLocation());
    }

    @Test
    void getActive() {
        //given
        Boolean isActive = false;

        //when
        eventTest.setActive(isActive);

        //then
        assertEquals(isActive, eventTest.getActive());
    }

    @Test
    void getContactsEmptyByDefault() {
        //given
        Set<Contact> contacts = new HashSet<>();

        //when
        eventTest.setContacts(contacts);

        //then
        assertEquals(contacts.isEmpty(), eventTest.getContacts().isEmpty());
    }

    @Test
    void getContacts() {
        //given
        Contact contact = new Contact();
        contact.setId(1l);
        Set<Contact> contacts = new HashSet<>();
        contacts.add(contact);

        //when
        eventTest.setContacts(contacts);

        //then
        assertEquals(contacts, eventTest.getContacts());
    }
}