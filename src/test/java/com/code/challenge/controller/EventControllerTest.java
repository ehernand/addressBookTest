package com.code.challenge.controller;

import com.code.challenge.Util;
import com.code.challenge.domain.Event;
import com.code.challenge.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
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
class EventControllerTest {

    @Mock
    EventService eventService;

    @InjectMocks
    EventController controller;

    MockMvc mockMvc;
    Event eventTest;
    Long eventTestId = 1L;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
        eventTest = new Event();
        eventTest.setId(eventTestId);
        eventTest.setStartDateTime(LocalDateTime.now());
        eventTest.setEndDateTime(LocalDateTime.now());
    }

    @Test
    @Disabled
    void createEvent() throws Exception {
        eventTest.setId(null);

        Event onceSaved = new Event();
        onceSaved.setId(eventTestId);

        when(eventService.save(any())).thenReturn(onceSaved);

        mockMvc.perform(post("/v1/events")
                .content(Util.asJsonString(eventTest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.location").isEmpty());
    }

    @Test
    @Disabled
    void getAllEvents() throws Exception {
        //given
        List<Event> events = Arrays.asList(eventTest);
        Page<Event> pagedResponse = new PageImpl(events);

        //when
        when(eventService.findAll(any())).thenReturn(pagedResponse);

        //then
        mockMvc.perform(get("/v1/events"))
                .andExpect(status().isOk());
    }

    @Test
    void getEvent() throws Exception {
        //given

        //when
        when(eventService.findOne(any())).thenReturn(Optional.of(eventTest));

        //then
        mockMvc.perform(get("/v1/events/" + eventTest.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void getNotExistingEvent() throws Exception {
        //given

        //when
        when(eventService.findOne(any())).thenReturn(Optional.empty());

        //then
        mockMvc.perform(get("/v1/events/2"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteEvent() throws Exception {
        //given

        //when
        when(eventService.findOne(any())).thenReturn(Optional.of(eventTest));

        //then
        mockMvc.perform(delete("/v1/events/" + eventTest.getId()))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteNotExistingEvent() throws Exception {
        //given

        //when
        when(eventService.findOne(any())).thenReturn(Optional.empty());

        //then
        mockMvc.perform(delete("/v1/events/2"))
                .andExpect(status().isNotFound());
    }
}