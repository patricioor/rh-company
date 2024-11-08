package io.github.patricioor.rh_company.exception;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException(String message){
        super(message + " não localizado.");
    }
}
