package com.code.challenge.exception;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(Long id) {
        super("Event id not found : " + id);
    }

    public EventNotFoundException(String message) {
        super(message);
    }

}
