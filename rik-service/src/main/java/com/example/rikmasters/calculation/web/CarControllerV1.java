package com.example.rikmasters.calculation.web;

import com.example.rikmasters.calculation.entity.model.car.DtoCarRequest;
import com.example.rikmasters.calculation.entity.model.car.DtoCarResponse;
import com.example.rikmasters.calculation.entity.model.constant.AccountStatus;
import com.example.rikmasters.calculation.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/car")
@Tag(name = "Контроллер автомобилей", description = "Контролер для работы с сущностью Car")
@ApiResponses(
        value = {
                @ApiResponse(responseCode = "200", description = "Создано"),
                @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных"),
                @ApiResponse(responseCode = "404", description = "Обьект не найден"),
                @ApiResponse(responseCode = "409", description = "Конфликт базы данных"),
                @ApiResponse(responseCode = "500", description = "Непредвиденная ошибка"),
        }
)
public class CarControllerV1 {
    private final CarService carService;

    @Autowired
    public CarControllerV1(CarService carService) {
        this.carService = carService;
    }

    @Operation(
            summary = "Создание нового автомобиля",
            description = "Создает новый автомобиль и присваивает id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Возвращает новый автомобиль",
            content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DtoCarResponse.class))
                    )
            })
    @PostMapping
    public ResponseEntity<DtoCarResponse> createCar(
            @Parameter(description = "DTO сущности") @Valid @RequestBody final DtoCarRequest dtoCarRequest
    ) {
        return new ResponseEntity<>(carService.createCar(dtoCarRequest), HttpStatus.OK);
    }

    @Operation(
            summary = "Поиск автомобиля по id",
            description = "Ищет и возвращает автомобиля согласно id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Возращает автомобиль",
            content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DtoCarResponse.class))
                    )
            })
    @GetMapping()
    public ResponseEntity<DtoCarResponse> findCarByStateNumber(
            @Parameter(description = "id пользователя") @RequestParam final String stateNumber
    ) {
        return new ResponseEntity<>(carService.findCarByStateNumber(stateNumber), HttpStatus.OK);
    }

    @Operation(
            summary = "Поиск всех автомобилей",
            description = "Ищет и возвращает списиок автомобиль согласно параметрам"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Возращает псотранично найденный список",
            content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DtoCarResponse.class))
                    )
            })
    @GetMapping("/all")
    public ResponseEntity<List<DtoCarResponse>> findAllCars(
            @Parameter(description = "Число страниц") @RequestParam(defaultValue = "0") final int from,
            @Parameter(description = "Количество объектов") @RequestParam(defaultValue = "5") final int size,
            @Parameter(description = "Сортировка") @RequestParam(defaultValue = "ASC") final String sort
    ) {
        return new ResponseEntity<>(carService.findAllCars(from, size, sort), HttpStatus.OK);
    }

    @Operation(
            summary = "Меняет статус акаунта пользователя",
            description = "Меняет статус акаунта согласно запросу (ACTIVE, DELETED) пользователю по id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Возвращает измененный объект",
            content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DtoCarResponse.class))
                    )
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<DtoCarResponse> setCarAccountStatus(
            @Parameter(description = "Id автомобиля") @PathVariable("id") final Long carId,
            @Parameter(description = "Статус аккаунта автомобиля") @RequestParam("status") final AccountStatus accountStatus
    ) {
        return new ResponseEntity<>(carService.setAccountStatus(carId, accountStatus), HttpStatus.OK);
    }

    @Operation(
            summary = "Присваивает владельца автомобилю",
            description = "Присваивает id владельца автомобилю "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Возвращает измененный объект",
            content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DtoCarResponse.class))
                    )
            })
    @PatchMapping("/{id}/owner/{ownerId}")
    public ResponseEntity<DtoCarResponse> setCarOwner(
            @Parameter(description = "Id автомобиля") @PathVariable("id") final Long carId,
            @Parameter(description = "Id пользователя") @PathVariable("ownerId") final Long ownerId
    ) {
        return new ResponseEntity<>(carService.setCarOwner(carId, ownerId), HttpStatus.OK);
    }

    @Operation(
            summary = "Позволяет добавлять и удалять детали в автомобиле",
            description = "Присваивает id владельца автомобилю "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Возвращает измененный объект",
            content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DtoCarResponse.class))
                    )
            })
    @PatchMapping("/{id}")
    public ResponseEntity<DtoCarResponse> setCarDetail(
            @Parameter(description = "Id автомобиля") @PathVariable("id") final Long carId,
            @Parameter(description = "Id детали") @RequestParam("detail") final Long detailId,
            @Parameter(description = "Действие с деталью") @RequestParam(value = "interaction") final String interaction
    ) {
        return new ResponseEntity<>(carService.setCarDetail(carId, detailId, interaction), HttpStatus.OK);
    }
}