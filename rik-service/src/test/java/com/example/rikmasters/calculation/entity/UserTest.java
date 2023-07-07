package com.example.rikmasters.calculation.entity;


import com.example.rikmasters.calculation.entity.model.constant.AccountStatus;
import com.example.rikmasters.calculation.entity.model.licernse.DtoLicenseCategoryShort;
import com.example.rikmasters.calculation.entity.model.user.DtoUserRequest;
import com.example.rikmasters.calculation.entity.model.user.DtoUserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@JsonTest
public class UserTest {
    private final Date date = new Date(System.currentTimeMillis());

    @Autowired
    private JacksonTester<User> json;
    @Autowired
    private JacksonTester<DtoUserRequest> jsonDtoUserRequest;
    @Autowired
    private JacksonTester<DtoUserResponse> jsonDtoUserResponse;

    @Test
    @DisplayName("Проверяем правильность сериализации User.")
    void whenCreateUserAndSerializableHim() throws Exception {

        final User user = User.builder()
                .id(1L)
                .fullName("Test Test Test")
                .passport("test")
                .birthday(date)
                .drivingExperience(3)
                .userLicense(Collections.singleton(LicenseCategory.builder().build()))
                .accountStatus(AccountStatus.ACTIVE)
                .build();

        JsonContent<User> result = json.write(user);

        //test
        assertThat(result).extractingJsonPathNumberValue("$.id").isEqualTo(1);
        assertThat(result).extractingJsonPathStringValue("$.fullName").isEqualTo("Test Test Test");
        assertThat(result).extractingJsonPathStringValue("$.passport").isEqualTo("test");
        assertThat(result).extractingJsonPathStringValue("$.birthday").isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.drivingExperience").isEqualTo(3);
        assertThat(result).extractingJsonPathValue("$.userLicense.[0]").isNotNull();
        assertThat(result).extractingJsonPathStringValue("$.accountStatus").isEqualTo(AccountStatus.ACTIVE.toString());
    }

    @Test
    @DisplayName("Проверяем правильность сериализации DtoUserRequest.")
    void whenCreateDtoUserRequestAndSerializableHim() throws Exception {

        final DtoUserRequest dtoUserRequest = DtoUserRequest.builder()
                .fullName("Test Test Test")
                .passport("test")
                .birthday(date)
                .drivingExperience(3)
                .userLicense(Collections.singleton(1L))
                .accountStatus(AccountStatus.ACTIVE)
                .build();

        JsonContent<DtoUserRequest> result = jsonDtoUserRequest.write(dtoUserRequest);

        //test
        assertThat(result).extractingJsonPathStringValue("$.fullName").isEqualTo("Test Test Test");
        assertThat(result).extractingJsonPathStringValue("$.passport").isEqualTo("test");
        assertThat(result).extractingJsonPathStringValue("$.birthday").isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.drivingExperience").isEqualTo(3);
        assertThat(result).extractingJsonPathValue("$.userLicense.[0]").isNotNull();
        assertThat(result).extractingJsonPathStringValue("$.accountStatus").isEqualTo(AccountStatus.ACTIVE.toString());
    }

    @Test
    @DisplayName("Проверяем правильность сериализации DtoUserResponse.")
    void whenCreateDtoUserResponseAndSerializableHim() throws Exception {

        final DtoUserResponse dtoUserResponse = DtoUserResponse.builder()
                .fullName("Test Test Test")
                .passport("test")
                .birthday(date)
                .drivingExperience(3)
                .userLicense(Collections.singleton(DtoLicenseCategoryShort.builder().build()))
                .accountStatus(AccountStatus.ACTIVE)
                .build();

        JsonContent<DtoUserResponse> result = jsonDtoUserResponse.write(dtoUserResponse);

        //test
        assertThat(result).extractingJsonPathStringValue("$.fullName").isEqualTo("Test Test Test");
        assertThat(result).extractingJsonPathStringValue("$.passport").isEqualTo("test");
        assertThat(result).extractingJsonPathStringValue("$.birthday").isNotNull();
        assertThat(result).extractingJsonPathNumberValue("$.drivingExperience").isEqualTo(3);
        assertThat(result).extractingJsonPathValue("$.userLicense.[0]").isNotNull();
        assertThat(result).extractingJsonPathStringValue("$.accountStatus").isEqualTo(AccountStatus.ACTIVE.toString());
    }
}