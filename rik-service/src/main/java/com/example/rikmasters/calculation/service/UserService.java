package com.example.rikmasters.calculation.service;


import com.example.rikmasters.calculation.entity.User;
import com.example.rikmasters.calculation.entity.model.constant.AccountStatus;
import com.example.rikmasters.calculation.entity.model.user.DtoUserRequest;
import com.example.rikmasters.calculation.entity.model.user.DtoUserResponse;

import java.util.List;

public interface UserService {
    DtoUserResponse createUser(DtoUserRequest dtoUserRequest);

    DtoUserResponse findById(Long userId);

    List<DtoUserResponse> findAllUsers(int from, int size, String sort);

    DtoUserResponse setAccountStatus(Long userId, AccountStatus accountStatus);

    User findUserById(Long userId);

}
