package io.github.patricioor.rh_company.domain.exception;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException(String message){
        super(message + " não localizado.");
    }
}
