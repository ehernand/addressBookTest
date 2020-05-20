package com.code.challenge.controller;

import com.code.challenge.Util;
import com.code.challenge.domain.Contact;
import com.code.challenge.service.ContactService;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class ContactControllerTest {

    @Mock
    ContactService contactService;

    @InjectMocks
    ContactController controller;

    MockMvc mockMvc;
    Contact contactTest;
    Long contactTestId = 1L;
    Pageable pageable;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                    .standaloneSetup(controller)
                    .build();
        contactTest = new Contact();
        contactTest.setId(contactTestId);

        //pageable = PageRequest.of(2, 20);
    }

    @Test
    void createContact() throws Exception {
        contactTest.setId(null);

        Contact onceSaved = new Contact();
        onceSaved.setId(contactTestId);

        when(contactService.save(contactTest)).thenReturn(onceSaved);

        mockMvc.perform(post("/v1/contacts")
                .content(Util.asJsonString(contactTest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").isEmpty());
    }

    @Test
    @Disabled
    void getEmptyContacts() throws Exception {
        //given
        List<Contact> contacts = Collections.emptyList();
        Page<Contact> pagedResponse = new PageImpl(contacts);

        //when
        when(contactService.findAll(pageable)).thenReturn(pagedResponse);

        //then
        mockMvc.perform(get("/v1/contacts"))
                .andExpect(status().isOk());
    }

    @Test
    @Disabled
    void getAllContacts() throws Exception {
        //given
        List<Contact> contacts = Arrays.asList(contactTest);
        Page<Contact> pagedResponse = new PageImpl(contacts);

        //when
        when(contactService.findAll(pageable)).thenReturn(pagedResponse);

        //then
        mockMvc.perform(get("/v1/contacts", pageable))
                .andExpect(status().isOk());
    }

    @Test
    void getContact() throws Exception {
        //given

        //when
        when(contactService.findOne(any())).thenReturn(Optional.of(contactTest));

        //then
        mockMvc.perform(get("/v1/contacts/" + contactTest.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void getNotExistingContact() throws Exception {
        //given

        //when
        when(contactService.findOne(any())).thenReturn(Optional.empty());

        //then
        mockMvc.perform(get("/v1/contacts/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteContact() throws Exception {
        //given

        //when
        when(contactService.findOne(any())).thenReturn(Optional.of(contactTest));

        //then
        mockMvc.perform(delete("/v1/contacts/" + contactTest.getId()))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteNotExistingContact() throws Exception {
        //given

        //when
        when(contactService.findOne(any())).thenReturn(Optional.empty());

        //then
        mockMvc.perform(delete("/v1/contacts/2"))
                .andExpect(status().isNotFound());
    }
}