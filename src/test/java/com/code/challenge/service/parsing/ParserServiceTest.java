package com.code.challenge.service.parsing;

import com.code.challenge.exception.InvalidFileException;
import com.code.challenge.exception.NotSuportedFileException;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParserServiceTest {

    @InjectMocks
    ParserService parserService;

    @Mock
    MultipartFile file;

    @BeforeEach
    void setUp() {
    }

    @Test
    void parseDataExpectInvalidFileException() {
        //given

        //when

        //then
        Assertions.assertThrows(InvalidFileException.class, () -> {
            parserService.parseData(null);
        });
    }

    @Test
    void parseDataExpectInvalidFile() {
        //given

        //when
        when(file.getOriginalFilename()).thenReturn("invalid");

        //then
        Assertions.assertThrows(NotSuportedFileException.class, () -> {
            parserService.parseData(file);
        });
    }

}