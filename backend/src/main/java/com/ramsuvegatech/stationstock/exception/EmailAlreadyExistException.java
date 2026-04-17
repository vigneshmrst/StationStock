package com.ramsuvegatech.stationstock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class EmailAlreadyExistException extends RuntimeException {
    public EmailAlreadyExistException(String email) {
        super("An account with email '" + email + "' already exists");
    }
}