package com.code.challenge.exception;

public class ContactNotFoundException extends RuntimeException {

    public ContactNotFoundException(Long id) {
        super("Contact id not found : " + id);
    }

    public ContactNotFoundException(String message) {
        super(message);
    }

}
