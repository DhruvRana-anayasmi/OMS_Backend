package com.example.OMS.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.OMS.dto.ApiError;
import org.springframework.security.access.AccessDeniedException;

import javax.management.relation.RoleNotFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException exp){
        return new ResponseEntity<>(
                new ApiError(exp.getMessage(), HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND
        );
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiError> handleBadCredentials() {
        return new ResponseEntity<>(
                new ApiError("Invalid username or password", HttpStatus.UNAUTHORIZED.value()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDenied() {
        return new ResponseEntity<>(
                new ApiError("You are not allowed to access this resource", HttpStatus.FORBIDDEN.value()),
                HttpStatus.FORBIDDEN
        );
    }



    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<ApiError> handleInventoryNotFound(Exception ex) {
        return new ResponseEntity<>(
                new ApiError("Inventory not found", HttpStatus.INTERNAL_SERVER_ERROR.value()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

@ExceptionHandler(InsufficientQuantityException.class)
public ResponseEntity<ApiError> handleInsufficientQuantity(Exception ex) {
    return new ResponseEntity<>(
            new ApiError("Insufficient inventory", HttpStatus.INTERNAL_SERVER_ERROR.value()),
            HttpStatus.INTERNAL_SERVER_ERROR
    );
}


    @ExceptionHandler(RoleAlreadyAssignedException.class)
    public ResponseEntity<ApiError> RoleAlreadyAssigned(Exception ex) {
        return new ResponseEntity<>(
                new ApiError("Role already assigned", HttpStatus.FORBIDDEN.value()),
                HttpStatus.FORBIDDEN
        );}

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ApiError> RoleNotFound(Exception ex) {
        return new ResponseEntity<>(
                new ApiError("Role not found", HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND
        );}
}
