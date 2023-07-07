package com.example.rikmasters.calculation.entity.mapper;

import com.example.rikmasters.calculation.entity.Car;
import com.example.rikmasters.calculation.entity.model.car.DtoCarRequest;
import com.example.rikmasters.calculation.entity.model.car.DtoCarResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper {

    Car toCar(DtoCarRequest dtoCarRequest);

    DtoCarResponse toDtoCarResponse(Car car);

    List<DtoCarResponse> toDtoCarResponses(List<Car> cars);
}
