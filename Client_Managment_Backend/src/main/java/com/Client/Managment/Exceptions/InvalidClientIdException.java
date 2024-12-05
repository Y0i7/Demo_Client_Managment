package com.Client.Managment.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidClientIdException extends RuntimeException{
    public InvalidClientIdException(String message){
        super(message);
    }
}
