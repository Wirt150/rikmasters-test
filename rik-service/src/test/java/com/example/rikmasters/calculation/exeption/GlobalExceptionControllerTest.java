package com.example.rikmasters.calculation.exeption;

import com.example.rikmasters.calculation.config.exception.ApiError;
import com.example.rikmasters.calculation.config.exception.GlobalExceptionController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class GlobalExceptionControllerTest {
    @Autowired
    private GlobalExceptionController exceptionController;
    @Autowired
    private WebRequest webRequest;

    @Test
    @DisplayName("Проверяем handleThrowable GlobalExceptionController")
    void handleThrowableTest() {
        List<ApiError> responseEntity = List.of(
                exceptionController.handleThrowable(new Throwable("test"), webRequest)
        );

        //test
        responseEntity.forEach(api -> test(api, HttpStatus.INTERNAL_SERVER_ERROR, "Непредвиденная ошибка: uri="));
    }

    @Test
    @DisplayName("Проверяем handleEntityNotFoundException GlobalExceptionController")
    void handleEntityNotFoundExceptionTest() {
        List<ApiError> responseEntity = List.of(
                exceptionController.handleEntityNotFoundException(new UserNotFoundException(1L), webRequest),
                exceptionController.handleEntityNotFoundException(new CarNotFoundException(String.valueOf(1L)), webRequest)
        );

        //test
        responseEntity.forEach(api -> test(api, HttpStatus.NOT_FOUND, "Ошибка поданных данных: uri="));
    }

    @Test
    @DisplayName("Проверяем handleForbiddenException GlobalExceptionController")
    void handleForbiddenExceptionTest() {
        List<ApiError> responseEntity = List.of(
                exceptionController.handleForbiddenException(new ValueWrongException(String.valueOf(1L)), webRequest)
        );

        //test
        responseEntity.forEach(api -> test(api, HttpStatus.FORBIDDEN, "Доступ к ресурсу запрещен: uri="));
    }

    @Test
    @DisplayName("Проверяем handleThrowableDataIntegrityViolation GlobalExceptionController")
    void handleThrowableDataIntegrityViolationTest() {
        ApiError responseEntity = exceptionController.handleThrowableDataIntegrityViolation(new DataIntegrityViolationException("test"), webRequest);

        //test
        test(responseEntity, HttpStatus.CONFLICT, "Нарушение целостности SQL данных: uri=");
    }

    @Test
    @DisplayName("Проверяем handleServletException GlobalExceptionController")
    void handleServletExceptionTest() {
        ApiError responseEntity = exceptionController.handleServletException(new MissingServletRequestParameterException("test", "test"), webRequest);

        //test
        test(responseEntity, HttpStatus.BAD_REQUEST, "В запросе не указанны обязательные параметры: uri=");
    }

    void test(ApiError responseEntity, HttpStatus status, String message) {
        assertNotNull(responseEntity);
        assertEquals(1, responseEntity.getErrors().size());
        assertNotNull(responseEntity.getMessage());
        assertEquals(message, responseEntity.getReason());
        assertEquals(status, responseEntity.getStatus());
        assertNotNull(responseEntity.getTimestamp());
    }
}
