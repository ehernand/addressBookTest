package com.code.challenge.service.Impl;

import com.code.challenge.domain.Contact;
import com.code.challenge.repository.ContactRepository;
import com.code.challenge.service.ContactService;
import com.code.challenge.service.parsing.ParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Service for managing {@link com.code.challenge.domain.Contact}.
 */

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

    private final Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);

    private final ContactRepository contactRepository;
    private final ParserService parserService;

    public ContactServiceImpl(final ContactRepository contactRepository,
                              final ParserService parserService) {
        this.contactRepository = contactRepository;
        this.parserService = parserService;
    }

    /**
     * Save a contact.
     *
     * @param contact the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Contact save(Contact contact) {
        log.debug("Request to save Contact : {}", contact);
        return contactRepository.save(contact);
    }

    /**
     * Get all the contacts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    public Page<Contact> findAll(Pageable pageable) {
        log.debug("Request to get all Contacts");
        return contactRepository.findAll(pageable);
    }

    /**
     * Get one contact by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<Contact> findOne(Long id) {
        log.debug("Request to get Contact : {}", id);
        return contactRepository.findById(id);
    }

    /**
     * Delete the contact by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Contact : {}", id);
        contactRepository.deleteById(id);
    }

    /**
     * Import contacts from a file.
     *
     * @param file the file with contacts to import.
     * @return the list of entities.
     */
    public List<Contact> importContacts(MultipartFile file) throws Exception {
        log.debug("Request to import Contacts");
        List<Contact> importedContacts = parserService.parseData(file);
        return contactRepository.saveAll(importedContacts);
    }
}
