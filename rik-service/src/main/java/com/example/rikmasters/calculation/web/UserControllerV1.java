package com.example.rikmasters.calculation.web;

import com.example.rikmasters.calculation.entity.model.constant.AccountStatus;
import com.example.rikmasters.calculation.entity.model.user.DtoUserRequest;
import com.example.rikmasters.calculation.entity.model.user.DtoUserResponse;
import com.example.rikmasters.calculation.service.UserService;
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
@RequestMapping("api/v1/user")
@Tag(name = "Контроллер пользователй", description = "Контролер для работы с сущностью User")
@ApiResponses(
        value = {
                @ApiResponse(responseCode = "200", description = "Создано"),
                @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных"),
                @ApiResponse(responseCode = "404", description = "Обьект не найден"),
                @ApiResponse(responseCode = "409", description = "Конфликт базы данных"),
                @ApiResponse(responseCode = "500", description = "Непредвиденная ошибка"),
        }
)
public class UserControllerV1 {
    private final UserService userService;

    @Autowired
    public UserControllerV1(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Создание нового пользователя",
            description = "Создает нового пользователя и присваивает id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Возвращает нового пользователя",
            content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DtoUserResponse.class))
                    )
            })
    @PostMapping
    public ResponseEntity<DtoUserResponse> createUser(
            @Parameter(description = "DTO сущности") @Valid @RequestBody final DtoUserRequest dtoUserRequest
    ) {
        return new ResponseEntity<>(userService.createUser(dtoUserRequest), HttpStatus.OK);
    }

    @Operation(
            summary = "Поиск пользователя по id",
            description = "Ищет и возвращает пользователя согласно id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Возращает пользователя",
            content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DtoUserResponse.class))
                    )
            })
    @GetMapping("/{id}")
    public ResponseEntity<DtoUserResponse> findUser(
            @Parameter(description = "id пользователя") @PathVariable final Long id
    ) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Поиск всех пользователей",
            description = "Ищет и возвращает списиок пользователей согласно параметрам"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Возращает псотранично найденный список",
            content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DtoUserResponse.class))
                    )
            })
    @GetMapping("/all")
    public ResponseEntity<List<DtoUserResponse>> findAllUsers(
            @Parameter(description = "Число страниц") @RequestParam(defaultValue = "0") final int from,
            @Parameter(description = "Количество объектов") @RequestParam(defaultValue = "5") final int size,
            @Parameter(description = "Сортировка") @RequestParam(defaultValue = "ASC") final String sort
    ) {
        return new ResponseEntity<>(userService.findAllUsers(from, size, sort), HttpStatus.OK);
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
                            array = @ArraySchema(schema = @Schema(implementation = DtoUserResponse.class))
                    )
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<DtoUserResponse> setUserAccountStatus(
            @Parameter(description = "Id пользователя") @PathVariable("id") final Long userId,
            @Parameter(description = "Статус аккаунта пользовтеля") @RequestParam("status") final AccountStatus accountStatus
    ) {
        return new ResponseEntity<>(userService.setAccountStatus(userId, accountStatus), HttpStatus.OK);
    }
}
