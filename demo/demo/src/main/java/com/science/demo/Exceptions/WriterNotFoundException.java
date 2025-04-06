package com.science.demo.Exceptions;

public class WriterNotFoundException extends RuntimeException {
    public WriterNotFoundException(String s) {
        super(s);
    }
}
