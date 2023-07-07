package com.example.rikmasters.calculation.service;

import com.example.rikmasters.calculation.entity.model.car.DtoCarRequest;
import com.example.rikmasters.calculation.entity.model.car.DtoCarResponse;
import com.example.rikmasters.calculation.entity.model.constant.AccountStatus;

import java.util.List;

public interface CarService {
    DtoCarResponse createCar(DtoCarRequest dtoCarRequest);

    DtoCarResponse findCarByStateNumber(String stateNumber);

    List<DtoCarResponse> findAllCars(int from, int size, String sort);

    DtoCarResponse setAccountStatus(Long carId, AccountStatus accountStatus);

    DtoCarResponse setCarOwner(Long carId, Long ownerId);

    DtoCarResponse setCarDetail(Long carId, Long detailId, String interaction);
}
