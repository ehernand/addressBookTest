package com.code.challenge.service.parsing;

import com.code.challenge.domain.Contact;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Parser {

    List<Contact> parse(MultipartFile file) throws Exception;
}
