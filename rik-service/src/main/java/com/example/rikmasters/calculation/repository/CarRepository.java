package com.example.rikmasters.calculation.repository;

import com.example.rikmasters.calculation.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByStateNumberIgnoreCase(String stateNumber);

    boolean existsCarByIdAndOwner_Id(Long carId, Long ownerId);
}
