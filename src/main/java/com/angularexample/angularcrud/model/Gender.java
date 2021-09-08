package com.angularexample.angularcrud.model;

public enum Gender {

    MALE('M'),FEMALE('F');

    private char c;

    Gender(char c) {
        this.c = c;
    }

    public char getC() {
        return c;
    }
}
