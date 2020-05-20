package com.code.challenge.bootstrap;

import com.code.challenge.domain.Contact;
import com.code.challenge.domain.Event;
import com.code.challenge.service.ContactService;
import com.code.challenge.service.EventService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Component
public class LoadData implements CommandLineRunner {

    private final ContactService contactService;
    private final EventService eventService;

    Contact batman = new Contact();
    Contact spyderman = new Contact();

    private LoadData(final ContactService contactService, final EventService eventService) {
        this.contactService = contactService;
        this.eventService = eventService;
    }

    @Override
    public void run(final String... args) throws Exception {
        loadContacts();
        loadEvents();
    }

    private void loadContacts() {
        batman.setFirstName("Bruno");
        batman.setLastName("Diaz");
        batman.setActive(true);
        contactService.save(batman);

        spyderman.setFirstName("Peter");
        spyderman.setLastName("Parker");
        spyderman.setActive(true);
        contactService.save(spyderman);
    }

    private void loadEvents() {
        Event saturday = new Event();
        saturday.setActive(true);
        saturday.setLocation("Gotham city");
        saturday.setStartDateTime(LocalDateTime.now());
        saturday.setEndDateTime(LocalDateTime.now());
        Set<Contact> participants = new HashSet<>();
        participants.add(batman);
        participants.add(spyderman);

        saturday.setContacts(participants);
        eventService.save(saturday);

        Set<Event> events = new HashSet<>();
        events.add(saturday);
        batman.setEvents(events);
        spyderman.setEvents(events);
    }

}
