package com.code.challenge.service.parsing;

import com.code.challenge.domain.Contact;
import com.code.challenge.exception.InvalidFileException;
import com.code.challenge.exception.NotSuportedFileException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Service for managing parsing logic as a factory
 */

@Service
public class ParserService {

    /**
     * Create and return a parser object by looking on file type.
     *
     * @param file the file with contacts to import.
     * @return parser object based on file type.
     */
    private Parser getParser(MultipartFile file) throws Exception {
        Parser parser = null;
        String fileType = FilenameUtils.getExtension(file.getOriginalFilename());
        if (fileType.equalsIgnoreCase("csv")) {
             parser = new CSVParser();
        } else if (fileType.equalsIgnoreCase("xml")) {
             parser = new XMLParser();
        } else {
            throw new NotSuportedFileException(file.getOriginalFilename());
        }
        return parser;
    }

    /**
     * Import contacts from a file.
     *
     * @param file the file with contacts to import.
     * @return the list of new entities.
     */
    public List<Contact> parseData(MultipartFile file) throws Exception {
        if (file == null) throw new InvalidFileException("Invalid file");
        Parser parser = getParser(file);
        return parser.parse(file);
    }
}
