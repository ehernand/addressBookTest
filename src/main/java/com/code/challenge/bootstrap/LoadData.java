package com.code.challenge.bootstrap;

import com.code.challenge.domain.Contact;
import com.code.challenge.service.ContactService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LoadData implements CommandLineRunner {

    private final ContactService contactService;

    private LoadData(final ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    public void run(final String... args) throws Exception {
        loadContact();
    }

    private void loadContact() {
        Contact batman = new Contact();
        batman.setFirstName("Bruno");
        batman.setLastName("Dias");
        batman.setActive(true);
        contactService.save(batman);

        Contact spyderman = new Contact();
        spyderman.setFirstName("Peter");
        spyderman.setLastName("Parcker");
        spyderman.setActive(true);
        contactService.save(spyderman);

        //Contact superman = Contact.builder().firstName("").lastName("").active(true).build();
        //contactService.save(superman);
    }
}
