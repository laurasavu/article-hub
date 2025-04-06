package com.science.demo.Exceptions;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String s) {
        super(s);
    }
}
