package com.example.rikmasters.service.config.exception;

import jakarta.servlet.ServletException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice("com.example.rikmasters.service")
public class GlobalExceptionController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleThrowable(final Throwable ex, WebRequest request) {
        return ApiError.builder()
                .errors(List.of(ex.getClass().getName()))
                .message(ex.getLocalizedMessage())
                .reason("Непредвиденная ошибка: " + request.getDescription(false))
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
    }

//    @ExceptionHandler(value = {
//            UserNotFoundException.class,
//            ClimeNotFoundException.class,
//            UsernameNotFoundException.class
//    })
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ApiError handleEntityNotFoundException(final EntityNotFoundException ex, WebRequest request) {
//        return ApiError.builder()
//                .errors(List.of(ex.getClass().getName()))
//                .message(ex.getLocalizedMessage())
//                .reason("Ошибка поданных данных: " + request.getDescription(false))
//                .status(HttpStatus.NOT_FOUND)
//                .build();
//    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleThrowableDataIntegrityViolation(final DataIntegrityViolationException ex, WebRequest request) {
        return ApiError.builder()
                .errors(List.of(ex.getClass().getName()))
                .message(ex.getLocalizedMessage())
                .reason("Нарушение целостности SQL данных: " + request.getDescription(false))
                .status(HttpStatus.CONFLICT)
                .build();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleServletException(final ServletException ex, WebRequest request) {
        return ApiError.builder()
                .errors(List.of(ex.getClass().getName()))
                .message(ex.getLocalizedMessage())
                .reason("В запросе не указанны обязательные параметры: " + request.getDescription(false))
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleOnMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> errors.add(e.getField() + ": " + e.getDefaultMessage()));
        ex.getBindingResult().getGlobalErrors().forEach(e -> errors.add(e.getObjectName() + ": " + e.getDefaultMessage()));
        return ApiError.builder()
                .errors(errors)
                .message(ex.getLocalizedMessage())
                .reason("Ошибка валидации входящих данных: " + request.getDescription(false))
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }
}
