package com.code.challenge.service.parsing;

import com.code.challenge.domain.Contact;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

public class XMLParser implements Parser {

    @Override
    public List<Contact> parse(MultipartFile file) throws Exception {
        System.out.println("XMLParser");
        return Collections.emptyList();
    }
}
