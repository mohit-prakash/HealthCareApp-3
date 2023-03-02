package com.mps.exception;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(){}
    public PatientNotFoundException(String message){
        super(message);
    }
}
