package com.code.challenge.controller;

import com.code.challenge.domain.Contact;
import com.code.challenge.exception.ContactNotFoundException;
import com.code.challenge.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import util.HeaderUtil;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.code.challenge.domain.Contact}.
 */
@RestController
public class ContactController {

    private final Logger log = LoggerFactory.getLogger(ContactController.class);

    private static final String ENTITY_NAME = "contact";

    @Value("${app.name}")
    private String applicationName;

    public static final String REST_URI_PREFIX = "/v1/contacts";

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    /**
     * {@code POST /contacts} : Create a new contact.
     *
     * @param contact the contact to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contact,
     * or with status {@code 400 (Bad Request)} if the contact has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping(REST_URI_PREFIX)
    public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact contact) throws URISyntaxException {
        log.debug("REST request to save Contact : {}", contact);
        if (contact.getId() != null) {
            throw new ContactNotFoundException("A new contact cannot already have an ID");
        }
        Contact result = contactService.save(contact);
        return ResponseEntity.created(new URI(REST_URI_PREFIX + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true,
                        ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT /contacts} : Updates an existing contact.
     *
     * @param contact the contact to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contact,
     * or with status {@code 400 (Bad Request)} if the contact is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contact couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping(REST_URI_PREFIX)
    public ResponseEntity<Contact> updateContact(@Valid @RequestBody Contact contact) throws URISyntaxException {
        log.debug("REST request to update Contact : {}", contact);
        if (contact.getId() == null) {
            throw new ContactNotFoundException("Invalid id");
        }
        Contact result = contactService.save(contact);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true,
                        ENTITY_NAME, contact.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET /contacts} : get all the contacts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contacts in body.
     */
    @GetMapping(REST_URI_PREFIX)
    public ResponseEntity<List<Contact>> getAllContacts(Pageable pageable) {
        log.debug("REST request to get a page of Contacts");
        Page<Contact> page = contactService.findAll(pageable);
        //HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        //return ResponseEntity.ok().headers(headers).body(page.getContent());
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET /contacts/:id} : get the "id" contact.
     *
     * @param id the id of the contact to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contact, or with status {@code 404 (Not Found)}.
     */
    @GetMapping(REST_URI_PREFIX + "/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable Long id) {
        log.debug("REST request to get Contact : {}", id);
        Optional<Contact> result = contactService.findOne(id);
        if (result.isPresent()) {
            Optional<Contact> contact = contactService.findOne(id);
            return ResponseEntity.ok().body(contact.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@code DELETE /contacts/:id} : delete the "id" contact.
     *
     * @param id the id of the contact to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping(REST_URI_PREFIX + "/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        log.debug("REST request to delete Contact : {}", id);
        Optional<Contact> result = contactService.findOne(id);
        if (result.isPresent()) {
            contactService.delete(id);
            return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName,
                    true, ENTITY_NAME, id.toString())).build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
