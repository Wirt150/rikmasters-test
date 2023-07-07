package com.example.rikmasters.calculation.service;


import com.example.rikmasters.calculation.entity.LicenseCategory;
import com.example.rikmasters.calculation.entity.User;
import com.example.rikmasters.calculation.entity.mapper.UserMapper;
import com.example.rikmasters.calculation.entity.model.constant.AccountStatus;
import com.example.rikmasters.calculation.entity.model.licernse.DtoLicenseCategoryShort;
import com.example.rikmasters.calculation.entity.model.user.DtoUserRequest;
import com.example.rikmasters.calculation.entity.model.user.DtoUserResponse;
import com.example.rikmasters.calculation.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {
    private final Date date = new Date(System.currentTimeMillis());
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserMapper userMapper;
    private User user;
    private DtoUserRequest dtoUserRequest;
    private DtoUserResponse dtoUserResponse;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .fullName("Test Test Test")
                .passport("test")
                .birthday(date)
                .drivingExperience(3)
                .userLicense(Collections.singleton(LicenseCategory.builder().build()))
                .accountStatus(AccountStatus.ACTIVE)
                .build();
        dtoUserRequest = DtoUserRequest.builder()
                .fullName("Test Test Test")
                .passport("test")
                .birthday(date)
                .drivingExperience(3)
                .userLicense(Collections.singleton(1L))
                .accountStatus(AccountStatus.ACTIVE)
                .build();
        dtoUserResponse = DtoUserResponse.builder()
                .fullName("Test Test Test")
                .passport("test")
                .birthday(date)
                .drivingExperience(3)
                .userLicense(Collections.singleton(DtoLicenseCategoryShort.builder().build()))
                .accountStatus(AccountStatus.ACTIVE)
                .build();
        when(userMapper.toDtoUserResponse(any(User.class))).thenReturn(dtoUserResponse);
        when(userMapper.toUser(any(DtoUserRequest.class))).thenReturn(user);
        when(userMapper.toDtoUserResponses(anyList())).thenReturn(List.of(dtoUserResponse));
    }

    @AfterEach
    void mockVerify() {

        verifyNoMoreInteractions(
                userRepository,
                userMapper
        );
    }

    @Test
    @DisplayName("Проверяем метод createUser сервиса UserService.")
    void getCreateUserMethodTest() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        //test
        DtoUserResponse test = userService.createUser(dtoUserRequest);
        assertEquals("Test Test Test", test.getFullName());
        assertEquals("test", test.getPassport());
        assertNotNull(test.getBirthday());
        assertEquals(3, test.getDrivingExperience());
        assertEquals(1, test.getUserLicense().size());
        assertEquals(AccountStatus.ACTIVE, test.getAccountStatus());

        verify(userRepository, times(1)).save(user);
        verify(userMapper, times(1)).toUser(dtoUserRequest);
        verify(userMapper, times(1)).toDtoUserResponse(user);
    }

    @Test
    @DisplayName("Проверяем метод findById сервиса UserService.")
    void getFindByIdMethodTest() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        //test
        DtoUserResponse test = userService.findById(1L);
        assertEquals("Test Test Test", test.getFullName());
        assertEquals("test", test.getPassport());
        assertNotNull(test.getBirthday());
        assertEquals(3, test.getDrivingExperience());
        assertEquals(1, test.getUserLicense().size());
        assertEquals(AccountStatus.ACTIVE, test.getAccountStatus());

        verify(userRepository, times(1)).findById(anyLong());
        verify(userMapper, times(1)).toDtoUserResponse(user);
    }

    @Test
    @DisplayName("Проверяем метод findAllUsers сервиса UserService.")
    void findAllUsersMethodTest() {
        when(userRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(user)));

        //test
        List<DtoUserResponse> test = userService.findAllUsers(0, 10, "ASC");
        assertEquals(1, test.size());

        assertEquals("Test Test Test", test.get(0).getFullName());
        assertEquals("test", test.get(0).getPassport());
        assertNotNull(test.get(0).getBirthday());
        assertEquals(3, test.get(0).getDrivingExperience());
        assertEquals(1, test.get(0).getUserLicense().size());
        assertEquals(AccountStatus.ACTIVE, test.get(0).getAccountStatus());

        verify(userRepository, times(1)).findAll(any(Pageable.class));
        verify(userMapper, times(1)).toDtoUserResponses(anyList());
    }

    @Test
    @DisplayName("Проверяем метод setAccountStatus сервиса UserService.")
    void getSetAccountStatusMethodTest() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        //test
        DtoUserResponse test = userService.setAccountStatus(1L, AccountStatus.ACTIVE);
        assertEquals("Test Test Test", test.getFullName());
        assertEquals("test", test.getPassport());
        assertNotNull(test.getBirthday());
        assertEquals(3, test.getDrivingExperience());
        assertEquals(1, test.getUserLicense().size());
        assertEquals(AccountStatus.ACTIVE, test.getAccountStatus());

        verify(userRepository, times(1)).findById(anyLong());
        verify(userMapper, times(1)).toDtoUserResponse(user);
    }

    @Test
    @DisplayName("Проверяем метод findUserById сервиса UserService.")
    void getFindUserByIdMethodTest() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        //test
        User test = userService.findUserById(1L);
        assertEquals(1L, test.getId());
        assertEquals("Test Test Test", test.getFullName());
        assertEquals("test", test.getPassport());
        assertNotNull(test.getBirthday());
        assertEquals(3, test.getDrivingExperience());
        assertEquals(1, test.getUserLicense().size());
        assertEquals(AccountStatus.ACTIVE, test.getAccountStatus());

        verify(userRepository, times(1)).findById(anyLong());
    }
}
