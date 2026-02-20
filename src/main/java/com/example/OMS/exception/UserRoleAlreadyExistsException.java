package com.example.OMS.exception;

public class UserRoleAlreadyExistsException extends RuntimeException {


    public UserRoleAlreadyExistsException(String message) {

        super(message);
    }
}