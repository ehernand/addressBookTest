package com.code.challenge.service.parsing;

import com.code.challenge.domain.Contact;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

/**
 * Parsing logic for XML files with Contact objects
 *
 * Because I couldn't gave more time on it, I left this class without content.
 *
 * However you can see the implementation of the factory pattern :)
 *
 */
public class XMLParser implements Parser {

    /**
     * Create Contact object from a xml file.
     *
     * @param file the file with contacts to import.
     * @return List of contact parsed from file.
     */
    @Override
    public List<Contact> parse(MultipartFile file) throws Exception {
        System.out.println("XMLParser");
        return Collections.emptyList();
    }
}
