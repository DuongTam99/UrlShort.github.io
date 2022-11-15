package com.example.demoUrlShortGroovy.exception

class ValidationError extends StandardError{
    List<FieldMessage> errors = new ArrayList<>();

    void addError(String fieldName,String message) {
        errors.add(new FieldMessage(fieldName,message))
    }



}
