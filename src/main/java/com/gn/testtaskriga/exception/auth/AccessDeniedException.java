package com.gn.testtaskriga.exception.auth;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(){
        super("Your account has no right to access this resource!");
    }
}
