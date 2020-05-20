package com.code.challenge.service.Impl;

import com.code.challenge.domain.Contact;
import com.code.challenge.exception.InvalidFileException;
import com.code.challenge.exception.NotSuportedFileException;
import com.code.challenge.repository.ContactRepository;
import com.code.challenge.service.parsing.ParserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collections;
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

    @Mock
    ParserService parserService;

    @Mock
    MultipartFile file;

    @InjectMocks
    ContactServiceImpl contactService;

    Contact contactTest;
    Pageable pageable;
    Long contactTestId = 1L;
    String contactTestFirstName = "John";
    String contactTestLastName = "Smith";
    String contactTestEmail = "test@test.com";

    @BeforeEach
    void setUp() {
        contactTest = new Contact();
        contactTest.setId(contactTestId);
        contactTest.setFirstName(contactTestFirstName);
        contactTest.setLastName(contactTestLastName);
        contactTest.setEmail(contactTestEmail);
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
        verify(contactRepository).save(any());
    }

    @Test
    void findAllWhenEmpty() {
        //given
        List<Contact> contacts = Collections.emptyList();
        Page<Contact> pagedResponse = new PageImpl(contacts);

        //when
        when(contactRepository.findAll(pageable)).thenReturn(pagedResponse);
        Page<Contact> results = contactService.findAll(pageable);

        //then
        assertNotNull(results);
        assertTrue(results.isEmpty());
        verify(contactRepository).findAll(pageable);
    }

    @Test
    void findAll() {
        //given
        List<Contact> contacts = Arrays.asList(contactTest);
        Page<Contact> pagedResponse = new PageImpl(contacts);

        //when
        when(contactRepository.findAll(pageable)).thenReturn(pagedResponse);
        Page<Contact> results = contactService.findAll(pageable);

        //then
        assertNotNull(results);
        assertFalse(results.isEmpty());
        verify(contactRepository).findAll(pageable);
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

    @Test
    void importContacts() throws Exception {
        //given
        List<Contact> contacts = Arrays.asList(contactTest);

        //when
        when(parserService.parseData(any())).thenReturn(contacts);

        List<Contact> results = contactService.importContacts(any());

        //then
        assertNotNull(results);
        assertFalse(contacts.isEmpty());
        verify(parserService).parseData(any());
        verify(contactRepository).saveAll(any());
    }

    @Test
    void importContactsInvalidFile() throws Exception {
        //given

        //when
        when(parserService.parseData(null)).thenThrow(InvalidFileException.class);

        Assertions.assertThrows(InvalidFileException.class, () -> {
            contactService.importContacts(null);
        });
    }

    @Test
    void importContactsInvalidTypeFile() throws Exception {
        //given

        //when
        when(parserService.parseData(file)).thenThrow(NotSuportedFileException.class);

        Assertions.assertThrows(NotSuportedFileException.class, () -> {
            contactService.importContacts(file);
        });
    }
}