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

/*
    https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
 */
public class CSVParser implements Parser {

    @Override
    public List<Contact> parse(MultipartFile file) throws Exception {
        CSVReader csvReader = null;
        List<Contact> results = new ArrayList<>();

        try {
            Reader reader = new InputStreamReader(file.getInputStream());
            csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
            String[] line;
            while ((line = csvReader.readNext()) != null) {
                System.out.println("Contact [id= " + line[0] + ", code= " + line[1] + " , name=" + line[2] + "]");
                results.add(getContactFromLine(line));
            }
        } catch (IOException e) {
            throw new InvalidFileException(e.getMessage());
        }

        return results;
    }

    private Contact getContactFromLine(String[] line) {
        Contact row = new Contact();
        row.setFirstName(line[0]);
        row.setLastName(line[1]);
        row.setNickName(line[2]);
        row.setEmail(line[3]);
        return row;
    }

}
