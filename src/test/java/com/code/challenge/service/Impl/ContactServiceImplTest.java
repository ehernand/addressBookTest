package com.code.challenge.service.Impl;

import com.code.challenge.domain.Contact;
import com.code.challenge.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class ContactServiceImplTest {

    ContactServiceImpl contactService;

    @MockBean
    ContactRepository contactRepository;

    Contact contact;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        contactService = new ContactServiceImpl(contactRepository);

        contact = new Contact();
    }

    @Test
    void save() {
        //given
        Long idTest = 1L;
        contact.setId(idTest);

        //when
        Contact result = contactService.save(contact);

        //then
        assertNotNull(result);
        assertEquals(contact.getId(), result.getId());
    }

    @Test
    void findAll() {
    }

    @Test
    void findOne() {
    }

    @Test
    void delete() {
    }
}