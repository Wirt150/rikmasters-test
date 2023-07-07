package com.example.rikmasters.calculation.web;

import com.example.rikmasters.calculation.entity.model.constant.AccountStatus;
import com.example.rikmasters.calculation.entity.model.licernse.DtoLicenseCategoryShort;
import com.example.rikmasters.calculation.entity.model.user.DtoUserRequest;
import com.example.rikmasters.calculation.entity.model.user.DtoUserResponse;
import com.example.rikmasters.calculation.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserControllerV1.class)
public class UserControllerV1Test {
    private final Date date = new Date(System.currentTimeMillis());
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    private DtoUserRequest dtoUserRequest;
    private DtoUserResponse dtoUserResponse;

    @BeforeEach
    void setUp() {
        dtoUserRequest = DtoUserRequest.builder()
                .fullName("Test Test Test")
                .passport("00 00 000000")
                .birthday(date)
                .drivingExperience(3)
                .userLicense(Collections.singleton(1L))
                .accountStatus(AccountStatus.ACTIVE)
                .build();
        dtoUserResponse = DtoUserResponse.builder()
                .fullName("Test Test Test")
                .passport("00 00 000000")
                .birthday(date)
                .drivingExperience(3)
                .userLicense(Collections.singleton(DtoLicenseCategoryShort.builder().build()))
                .accountStatus(AccountStatus.ACTIVE)
                .build();
    }

    @AfterEach
    void mockVerify() {
        verifyNoMoreInteractions(
                userService
        );
    }

    @Test
    @DisplayName("Проверяем эндпоинт createUser контроллера UserController.")
    void createUserTest() throws Exception {
        when(userService.createUser(any(DtoUserRequest.class))).thenReturn(dtoUserResponse);

        //test
        mvc.perform(post("/api/v1/user")
                        .content(mapper.writeValueAsString(dtoUserRequest))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName", is(dtoUserResponse.getFullName())))
                .andExpect(jsonPath("$.passport", is(dtoUserResponse.getPassport())))
                .andExpect(jsonPath("$.birthday", is(notNullValue())))
                .andExpect(jsonPath("$.drivingExperience", is(dtoUserResponse.getDrivingExperience())))
                .andExpect(jsonPath("$.userLicense.[0]", is(notNullValue())))
                .andExpect(jsonPath("$.accountStatus", is(dtoUserResponse.getAccountStatus().toString())));

        verify(userService, times(1)).createUser(any(DtoUserRequest.class));
    }

    @Test
    @DisplayName("Проверяем эндпоинт findUser контроллера UserController.")
    void findUserTest() throws Exception {
        when(userService.findById(anyLong())).thenReturn(dtoUserResponse);

        //test
        mvc.perform(get("/api/v1/user/1")
                        .param("userId", "1")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName", is(dtoUserResponse.getFullName())))
                .andExpect(jsonPath("$.passport", is(dtoUserResponse.getPassport())))
                .andExpect(jsonPath("$.birthday", is(notNullValue())))
                .andExpect(jsonPath("$.drivingExperience", is(dtoUserResponse.getDrivingExperience())))
                .andExpect(jsonPath("$.userLicense.[0]", is(notNullValue())))
                .andExpect(jsonPath("$.accountStatus", is(dtoUserResponse.getAccountStatus().toString())));

        verify(userService, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Проверяем эндпоинт findAllUsers контроллера UserController.")
    void findAllUsersTest() throws Exception {
        when(userService.findAllUsers(anyInt(), anyInt(), anyString())).thenReturn(List.of(dtoUserResponse));

        //test
        mvc.perform(get("/api/v1/user/all")
                        .param("from", "0")
                        .param("size", "10")
                        .param("sort", "ASC")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].fullName", is(dtoUserResponse.getFullName())))
                .andExpect(jsonPath("$[0].passport", is(dtoUserResponse.getPassport())))
                .andExpect(jsonPath("$[0].birthday", is(notNullValue())))
                .andExpect(jsonPath("$[0].drivingExperience", is(dtoUserResponse.getDrivingExperience())))
                .andExpect(jsonPath("$[0].userLicense.[0]", is(notNullValue())))
                .andExpect(jsonPath("$[0].accountStatus", is(dtoUserResponse.getAccountStatus().toString())));

        verify(userService, times(1)).findAllUsers(anyInt(), anyInt(), anyString());
    }

    @Test
    @DisplayName("Проверяем эндпоинт setUserAccountStatus контроллера UserController.")
    void setUserAccountStatusTest() throws Exception {
        when(userService.setAccountStatus(anyLong(), any(AccountStatus.class))).thenReturn(dtoUserResponse);

        //test
        mvc.perform(delete("/api/v1/user/1")
                        .param("userId", "1")
                        .param("status", "DELETED")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fullName", is(dtoUserResponse.getFullName())))
                .andExpect(jsonPath("$.passport", is(dtoUserResponse.getPassport())))
                .andExpect(jsonPath("$.birthday", is(notNullValue())))
                .andExpect(jsonPath("$.drivingExperience", is(dtoUserResponse.getDrivingExperience())))
                .andExpect(jsonPath("$.userLicense.[0]", is(notNullValue())))
                .andExpect(jsonPath("$.accountStatus", is(dtoUserResponse.getAccountStatus().toString())));

        verify(userService, times(1)).setAccountStatus(anyLong(), any(AccountStatus.class));
    }
}
