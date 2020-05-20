package com.code.challenge.service.parsing;

import com.code.challenge.domain.Contact;
import com.code.challenge.exception.InvalidFileException;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CSVParserTest {

    @InjectMocks
    CSVParser csvParser;

    @Mock
    MultipartFile file;

    @Mock
    CSVReader csvReader;

    @BeforeEach
    void setUp() {
    }

    @Test
    void parseWithFile() throws Exception {
        InputStream in = mock(InputStream.class);
        when(file.getInputStream()).thenReturn(in);

        csvParser.parse(file);
    }

    @Test
    void parseWithFileData() throws Exception {
        //given
        InputStream in = mock(InputStream.class);
        String[] line = new String[] {"1","2","3","4"};

        //when
        when(file.getInputStream()).thenReturn(in);
        when(csvReader.readNext()).thenReturn(line);

        List<Contact> result = csvParser.parse(file);

        assertNotNull(result);
    }

    @Test
    void parseWithFileException() throws Exception {
        //given
        InputStream in = mock(InputStream.class);

        //when
        when(file.getInputStream()).thenThrow(InvalidFileException.class);

        //then
        Assertions.assertThrows(InvalidFileException.class, () -> {
            csvParser.parse(file);
        });

    }
}