package com.code.challenge.service.Impl;

import com.code.challenge.domain.Event;
import com.code.challenge.repository.EventRepository;
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
class EventServiceImplTest {

    @Mock
    EventRepository eventRepository;

    @InjectMocks
    EventServiceImpl eventServiceService;

    Event eventTest;
    Pageable pageable;
    Long eventTestId = 1L;

    @BeforeEach
    void setUp() {
        eventTest = new Event();
        eventTest.setId(eventTestId);

        pageable = PageRequest.of(2, 20);
    }

    @Test
    void save() {
        //given

        //when
        when(eventRepository.save(any())).thenReturn(eventTest);
        Event result = eventServiceService.save(eventTest);

        //then
        assertNotNull(result);
        assertEquals(eventTest.getId(), result.getId());
        verify(eventRepository).save(any());
    }


    @Test
    void findAllWhenEmpty() {
        //given
        List<Event> events = Collections.emptyList();
        Page<Event> pagedResponse = new PageImpl(events);

        //when
        when(eventRepository.findAll(pageable)).thenReturn(pagedResponse);
        Page<Event> results = eventServiceService.findAll(pageable);

        //then
        assertNotNull(results);
        assertTrue(results.isEmpty());
        verify(eventRepository).findAll(pageable);
    }

    @Test
    void findAll() {
        //given
        List<Event> events = Arrays.asList(eventTest);
        Page<Event> pagedResponse = new PageImpl(events);

        //when
        when(eventRepository.findAll(pageable)).thenReturn(pagedResponse);
        Page<Event> results = eventServiceService.findAll(pageable);

        //then
        assertNotNull(results);
        assertFalse(results.isEmpty());
        verify(eventRepository).findAll(pageable);
    }

    @Test
    void findOneWhenEmpty() {
        //given

        //when
        when(eventRepository.findById(any())).thenReturn(Optional.empty());

        Optional<Event> result = eventServiceService.findOne(any());

        //then
        assertFalse(result.isPresent());
        verify(eventRepository).findById(any());
    }

    @Test
    void findOne() {
        //given

        //when
        when(eventRepository.findById(any())).thenReturn(Optional.of(eventTest));

        Optional<Event> result = eventServiceService.findOne(eventTest.getId());

        //then
        assertTrue(result.isPresent());
        assertEquals(eventTest, result.get());
        verify(eventRepository).findById(any());
    }

    @Test
    void delete() {
        //given

        //when
        eventServiceService.delete(any());

        //then
        verify(eventRepository).deleteById(any());
    }
}