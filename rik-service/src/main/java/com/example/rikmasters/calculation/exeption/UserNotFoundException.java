package com.example.rikmasters.calculation.exeption;

import jakarta.persistence.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(Long id) {
        super(String.format("User with id: %s not found", id));
    }
}
