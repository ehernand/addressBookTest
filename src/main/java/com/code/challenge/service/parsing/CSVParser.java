package com.code.challenge.service.parsing;

import com.code.challenge.domain.Contact;
import com.code.challenge.exception.InvalidFileException;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Parser logic for CSV files with Contact objects
 */
public class CSVParser implements Parser {

    /**
     * Create Contact object from a csv file.
     *
     * @param file the file with contacts to import.
     * @return List of contact parsed from file.
     */
    @Override
    public List<Contact> parse(MultipartFile file) throws Exception {
        CSVReader csvReader = null;
        List<Contact> results = new ArrayList<>();

        try {
            Reader reader = new InputStreamReader(file.getInputStream());
            csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                results.add(getContactFromLine(line));
            }
        } catch (IOException e) {
            throw new InvalidFileException(e.getMessage());
        }

        return results;
    }

    private Contact getContactFromLine(String[] line) {
        Contact contact = new Contact();
        contact.setFirstName(line[0]);
        contact.setLastName(line[1]);
        contact.setNickName(line[2]);
        contact.setEmail(line[3]);
        return contact;
    }

}
