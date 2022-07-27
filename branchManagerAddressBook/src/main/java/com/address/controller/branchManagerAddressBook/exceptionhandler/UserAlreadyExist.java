package com.address.controller.branchManagerAddressBook.exceptionhandler;


public class UserAlreadyExist extends Exception{
    public UserAlreadyExist(String message) {
        super(message);
    }
}
