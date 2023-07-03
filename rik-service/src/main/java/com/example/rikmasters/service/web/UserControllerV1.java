//package com.example.rikmasters.service.web;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.media.ArraySchema;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import ru.example.vitasoft.service.entity.mapper.UserMapper;
//import ru.example.vitasoft.service.entity.model.user.UserShortDto;
//import ru.example.vitasoft.service.security.constant.Role;
//import ru.example.vitasoft.service.service.UserService;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("api/v1/user")
//@Tag(name = "Контроллер пользователй", description = "Контролер для работы с сущностью User")
//@ApiResponses(
//        value = {
//                @ApiResponse(responseCode = "200", description = "Создано"),
//                @ApiResponse(responseCode = "400", description = "Ошибка валидации входных данных"),
//                @ApiResponse(responseCode = "404", description = "Обьект не найден"),
//                @ApiResponse(responseCode = "409", description = "Конфликт базы данных"),
//                @ApiResponse(responseCode = "500", description = "Непредвиденная ошибка"),
//        }
//)
//public class UserControllerV1 {
//    private final UserService userService;
//    private final UserMapper userMapper;
//
//    @Autowired
//    public UserControllerV1(UserService userService, UserMapper userMapper) {
//        this.userService = userService;
//        this.userMapper = userMapper;
//    }
//
//    @Operation(
//            summary = "Поиск всех пользователей",
//            description = "Ищет и возвращает списиок пользователей согласно параметрам"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Возращает псотранично найденный список",
//            content = {
//                    @Content(mediaType = "application/json",
//                            array = @ArraySchema(schema = @Schema(implementation = UserShortDto.class))
//                    )
//            })
//    @PreAuthorize("hasAnyAuthority('admin:read')")
//    @GetMapping("/all")
//    public ResponseEntity<List<UserShortDto>> findAllUsers(
//            @Parameter(description = "Число страниц") @RequestParam(defaultValue = "0") final int from,
//            @Parameter(description = "Количество объектов") @RequestParam(defaultValue = "5") final int size
//    ) {
//        return new ResponseEntity<>(userMapper.toUserShortDdos(userService.findAllUsers(from, size)), HttpStatus.OK);
//    }
//
//    @Operation(
//            summary = "Поиск определенного пользователя",
//            description = "Ищет пользователя по username или его части"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Возвращает найденого пользователя",
//            content = {
//                    @Content(mediaType = "application/json",
//                            array = @ArraySchema(schema = @Schema(implementation = UserShortDto.class))
//                    )
//            })
//    @PreAuthorize("hasAnyAuthority('admin:read')")
//    @GetMapping("/{user_name}")
//    public ResponseEntity<UserShortDto> findUserByName(
//            @Parameter(description = "Имя пользователя") @PathVariable("user_name") String username
//    ) {
//        return new ResponseEntity<>(userMapper.toUserShortDdo(userService.findUserByName(username)), HttpStatus.OK);
//    }
//
//    @Operation(
//            summary = "Добавляет новую роль акаунту пользователя",
//            description = "Добавляет новую роль согласно запросу (ADMIN, OPERATOR, USER) пользователю по id"
//    )
//    @ApiResponse(
//            responseCode = "200",
//            description = "Возвращает измененный объект",
//            content = {
//                    @Content(mediaType = "application/json",
//                            array = @ArraySchema(schema = @Schema(implementation = UserShortDto.class))
//                    )
//            })
//    @PreAuthorize("hasAnyAuthority('admin:write')")
//    @PatchMapping("/{id}")
//    public ResponseEntity<UserShortDto> setRole(
//            @Parameter(description = "Id пользователя") @PathVariable("id") final Long userId,
//            @Parameter(description = "Role пользователя") @RequestParam("role") final Role role
//    ) {
//        return new ResponseEntity<>(userMapper.toUserShortDdo(userService.setRole(userId, role)), HttpStatus.OK);
//    }
//}
