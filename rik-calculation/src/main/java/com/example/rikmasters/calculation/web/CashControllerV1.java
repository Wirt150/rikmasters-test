package com.example.rikmasters.calculation.web;

import com.example.rikmasters.calculation.entity.constant.ActionStatus;
import com.example.rikmasters.calculation.entity.constant.CashColor;
import com.example.rikmasters.calculation.entity.model.CashAccountResponse;
import com.example.rikmasters.calculation.service.CashService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("api/v1/cash")
@Tag(name = "Контроллер счёта", description = "Контролер для работы со счётом пользователей")
@ApiResponses(
        value = {
                @ApiResponse(responseCode = "200", description = "Создано"),
                @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных"),
                @ApiResponse(responseCode = "404", description = "Обьект не найден"),
                @ApiResponse(responseCode = "409", description = "Конфликт базы данных"),
                @ApiResponse(responseCode = "500", description = "Непредвиденная ошибка"),
        }
)
public class CashControllerV1 {
    private final CashService cashService;

    @Autowired
    public CashControllerV1(CashService cashService) {
        this.cashService = cashService;
    }

    @Operation(
            summary = "Создание счета",
            description = "Создает счет и присваивает id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Возвращает состояние счета",
            content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CashAccountResponse.class))
                    )
            })
    @PostMapping("/owner/{id}")
    public ResponseEntity<CashAccountResponse> createCashAccount(
            @Parameter(description = "id счета") @PathVariable("id") final Long id
    ) {
        return new ResponseEntity<>(cashService.createCashAccount(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Зачисление или вычитание денег на счет пользователя",
            description = "Позволяет работать со счетом (зачислять, снимать) в зависимости от подаваемых параметров"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Возвращает состояние счета",
            content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CashAccountResponse.class))
                    )
            })
    @PostMapping("/{id}")
    public ResponseEntity<CashAccountResponse> sendCash(
            @Parameter(description = "id счета") @PathVariable("id") final Long id,
            @Parameter(description = "Сумма зачисления") @RequestParam("sum") final BigDecimal sum,
            @Parameter(description = "Выбор валюты") @RequestParam("color") final CashColor cashColor,
            @Parameter(description = "Действие") @RequestParam("action") final ActionStatus actionStatus
    ) {
        return new ResponseEntity<>(cashService.sendCash(id, sum, cashColor, actionStatus), HttpStatus.OK);
    }

    @Operation(
            summary = "Запрос состояния счета",
            description = "Ищет счёт по id "
    )
    @ApiResponse(
            responseCode = "200",
            description = "Возвращает информацию согласно запросу",
            content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CashAccountResponse.class))
                    )
            })
    @GetMapping("/{id}")
    public ResponseEntity<CashAccountResponse> getCash(
            @Parameter(description = "id счета") @PathVariable("id") final Long id
    ) {
        return new ResponseEntity<>(cashService.getCash(id), HttpStatus.OK);
    }
}
