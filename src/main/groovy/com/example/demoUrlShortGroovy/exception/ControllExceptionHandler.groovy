package com.example.demoUrlShortGroovy.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ControllExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<StandardError> validation(MethodArgumentNotValidException e,HttpServletRequest request) {
    ValidationError error = new ValidationError(timestamp: System.currentTimeMillis(),status: HttpStatus.UNPROCESSABLE_ENTITY.value(),error: "Validation Error",e.getMessage(),request.getRequestURI())
    e.getBindingResult().getFieldErrors().stream().forEach(fieldError -> error.addError(fieldError.getField(),fieldError.getDefaultMessage()))
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);


    }


}
