package com.example.rikmasters.calculation.service.impl;

import com.example.rikmasters.calculation.entity.Car;
import com.example.rikmasters.calculation.entity.User;
import com.example.rikmasters.calculation.entity.mapper.CarMapper;
import com.example.rikmasters.calculation.entity.model.car.DtoCarRequest;
import com.example.rikmasters.calculation.entity.model.car.DtoCarResponse;
import com.example.rikmasters.calculation.entity.model.constant.AccountStatus;
import com.example.rikmasters.calculation.exeption.CarNotFoundException;
import com.example.rikmasters.calculation.exeption.UserNotFoundException;
import com.example.rikmasters.calculation.exeption.ValueWrongException;
import com.example.rikmasters.calculation.repository.CarRepository;
import com.example.rikmasters.calculation.repository.DetailRepository;
import com.example.rikmasters.calculation.service.CarService;
import com.example.rikmasters.calculation.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final DetailRepository detailRepository;
    private final UserService userService;
    private final CarMapper carMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, DetailRepository detailRepository, UserService userService, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.detailRepository = detailRepository;
        this.userService = userService;
        this.carMapper = carMapper;
    }

    @Override
    public DtoCarResponse createCar(final DtoCarRequest dtoCarRequest) {
        Car car = carRepository.save(carMapper.toCar(dtoCarRequest));
        return carMapper.toDtoCarResponse(car);
    }

    @Override
    public DtoCarResponse findCarByStateNumber(final String stateNumber) {
        Car car = carRepository.findByStateNumberIgnoreCase(stateNumber).orElseThrow(() -> new CarNotFoundException(stateNumber));
        return carMapper.toDtoCarResponse(car);
    }

    @Override
    public List<DtoCarResponse> findAllCars(final int from, final int size, String sort) {
        List<Car> cars = switch (sort.toUpperCase()) {
            case "ASC" -> carRepository.findAll(PageRequest.of(from, size, Sort.by("id"))).toList();
            case "DESC" -> carRepository.findAll(PageRequest.of(from, size, Sort.by("id").descending())).toList();
            default -> new ArrayList<>();
        };
        return carMapper.toDtoCarResponses(cars);
    }

    @Override
    public DtoCarResponse setAccountStatus(final Long carId, final AccountStatus accountStatus) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new UserNotFoundException(carId));
        car.setAccountStatus(accountStatus);
        return carMapper.toDtoCarResponse(car);
    }

    @Override
    public DtoCarResponse setCarOwner(final Long carId, final Long ownerId) {
        if (carRepository.existsCarByIdAndOwner_Id(carId, ownerId)) {
            throw new ValueWrongException(carId.toString());
        }
        User user = userService.findUserById(ownerId);
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId.toString()));
        car.setOwner(user);
        return carMapper.toDtoCarResponse(car);
    }

    @Override
    public DtoCarResponse setCarDetail(final Long carId, final Long detailId, String interaction) {
        if (!carRepository.existsById(carId)) {
            throw new CarNotFoundException(carId.toString());
        }
        switch (interaction.toUpperCase()) {
            case "ADD" -> detailRepository.addDetailCarId(carId, detailId);
            case "DEL" -> detailRepository.deletedDetailCarId(detailId);
        }
        return carMapper.toDtoCarResponse(carRepository.findById(carId).get());
    }
}
