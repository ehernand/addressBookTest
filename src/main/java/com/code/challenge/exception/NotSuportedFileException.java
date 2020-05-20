package com.code.challenge.exception;

public class NotSuportedFileException extends RuntimeException {

    public NotSuportedFileException(String message) {
        super("This type of file is not supported: " + message);
    }

}
