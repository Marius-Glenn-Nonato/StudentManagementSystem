package com.marius.sms.backend.exception;

public class InvalidChoiceException extends RuntimeException{
    public InvalidChoiceException(String message){
        super(message);
    }
}
