package com.code.challenge.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTypeTest {

    @Test
    public void notEmpty() {
        assertTrue(ContactType.values().length > 0);
    }

}