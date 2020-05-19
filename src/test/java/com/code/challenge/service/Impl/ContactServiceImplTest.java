package com.code.challenge.service.Impl;

import com.code.challenge.domain.Contact;
import com.code.challenge.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {

    @Mock
    ContactRepository contactRepository;

    @InjectMocks
    ContactServiceImpl contactService;

    Contact contactTest;
    Pageable pageable;
    Long contactTestId = 1L;
    String contactTestFirstName = "John";

    @BeforeEach
    void setUp() {
        contactTest = new Contact();
        contactTest.setId(contactTestId);
        contactTest.setFirstName(contactTestFirstName);

        pageable = PageRequest.of(2, 20);
    }

    @Test
    void save() {
        //given

        //when
        when(contactRepository.save(any())).thenReturn(contactTest);
        Contact result = contactService.save(contactTest);

        //then
        assertNotNull(result);
        assertEquals(contactTest.getId(), result.getId());
        //verify(contactRepository).save(any());
    }

    @Test
    @Disabled
    void findAll() {
        //given
        List<Contact> contacts = new ArrayList<>();
        contacts.add(contactTest);
        Page<Contact> pagedResponse = new PageImpl(contacts);

        //when
        //when(contactRepository.findAll(any())).thenReturn(pagedResponse);
        //when(contactRepository.findAll()).thenReturn(pagedResponse);
        Page<Contact> results = contactService.findAll(pageable);

        //then
        assertNotNull(results);
        assertFalse(results.isEmpty());
        verify(contactRepository).findAll();
    }

    @Test
    void findOneWhenEmpty() {
        //given

        //when
        when(contactRepository.findById(any())).thenReturn(Optional.empty());

        Optional<Contact> result = contactService.findOne(any());

        //then
        assertFalse(result.isPresent());
        verify(contactRepository).findById(any());
    }

    @Test
    void findOne() {
        //given

        //when
        when(contactRepository.findById(any())).thenReturn(Optional.of(contactTest));

        Optional<Contact> result = contactService.findOne(contactTest.getId());

        //then
        assertTrue(result.isPresent());
        assertEquals(contactTest, result.get());
        verify(contactRepository).findById(any());
    }

    @Test
    void delete() {
        //given

        //when
        contactService.delete(any());

        //then
        verify(contactRepository).deleteById(any());
    }

}