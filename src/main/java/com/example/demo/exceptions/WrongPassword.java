package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class WrongPassword extends RuntimeException{

    public WrongPassword() {
    }

    public WrongPassword(String message) {
        super(message);
    }

    public WrongPassword(String message, Throwable cause) {
        super(message, cause);
    }
}
