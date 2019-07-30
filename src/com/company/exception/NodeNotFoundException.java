package com.company.exception;

public class NodeNotFoundException extends RuntimeException{
    public NodeNotFoundException(String message){
        super(message);
    }
}
