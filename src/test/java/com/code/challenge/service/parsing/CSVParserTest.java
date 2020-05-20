package com.code.challenge.service.parsing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CSVParserTest {

    CSVParser csvParser;

    File test;

    @BeforeEach
    void setUp() {
        csvParser = new CSVParser();
    }

    @Test
    void parseThrowErrorFileNull() throws Exception {
        when(csvParser.parse(null)).thenThrow(Exception.class);
    }
}