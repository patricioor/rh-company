package io.github.patricioor.rh_company.domain.exception;

public class ElementAlreadyExistsException extends RuntimeException{
    public ElementAlreadyExistsException(String message) {
        super(message + " já existe.");
    }
}
