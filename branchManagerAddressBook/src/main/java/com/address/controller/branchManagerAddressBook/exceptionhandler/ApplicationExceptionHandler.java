package com.address.controller.branchManagerAddressBook.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleInvalidAddressTypeException(HttpMessageNotReadableException ex){
        ex.getHttpInputMessage();
        return "You have not entered correct address type";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNameNotFoundException.class)
    public Map<String, String> handleUserNotFoundException(UserNameNotFoundException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExist.class)
    public String handlerUserAlreadyExist(UserAlreadyExist ex){
        return ex.getLocalizedMessage();
//        return "User is already exist";
    }
}
