package com.gn.testtaskriga.exception.auth;

public class UserFoundException extends RuntimeException{
    public UserFoundException(String message) {
        super(message);
    }
}
