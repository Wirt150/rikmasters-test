package com.example.rikmasters.calculation.exeption;

public class CashAccountException extends RuntimeException {
    public CashAccountException(Long status) {
        super(String.format("CashAccount with id: %s not found", status));
    }
}
