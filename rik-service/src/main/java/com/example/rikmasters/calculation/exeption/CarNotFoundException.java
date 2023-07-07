package com.example.rikmasters.calculation.exeption;

import jakarta.persistence.EntityNotFoundException;

public class CarNotFoundException extends EntityNotFoundException {
    public CarNotFoundException(String id) {
        super(String.format("Car with id: %s not found", id));
    }
}
