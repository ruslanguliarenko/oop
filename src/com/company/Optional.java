package com.company;

import java.util.NoSuchElementException;

public class Optional<T> {
    private T value;

    public Optional(T value) {
        this.value = value;
    }

    public T orElse(T other){
        return value == null ? other : value;
    }
    public  boolean isPresent(){
        return value != null;
    }
    public T getValue() {
        if(value == null) throw  new NoSuchElementException();
        return value;
    }
}
