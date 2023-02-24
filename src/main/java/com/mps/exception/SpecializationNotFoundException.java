package com.mps.exception;

public class SpecializationNotFoundException extends RuntimeException{
    public SpecializationNotFoundException(){}
    public SpecializationNotFoundException(String message){
        super(message);
    }
}
