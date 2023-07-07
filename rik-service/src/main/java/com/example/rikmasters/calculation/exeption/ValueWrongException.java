package com.example.rikmasters.calculation.exeption;

public class ValueWrongException extends RuntimeException {
    public ValueWrongException(String id) {
        super(String.format("Wrong value error in car id: %s", id));
    }
}
