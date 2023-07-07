package com.example.rikmasters.calculation.service.impl;

import com.example.rikmasters.calculation.entity.User;
import com.example.rikmasters.calculation.entity.mapper.UserMapper;
import com.example.rikmasters.calculation.entity.model.constant.AccountStatus;
import com.example.rikmasters.calculation.entity.model.user.DtoUserRequest;
import com.example.rikmasters.calculation.entity.model.user.DtoUserResponse;
import com.example.rikmasters.calculation.exeption.UserNotFoundException;
import com.example.rikmasters.calculation.repository.UserRepository;
import com.example.rikmasters.calculation.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public DtoUserResponse createUser(final DtoUserRequest dtoUserRequest) {
        User user = userRepository.save(userMapper.toUser(dtoUserRequest));
        return userMapper.toDtoUserResponse(user);
    }

    @Override
    public DtoUserResponse findById(final Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return userMapper.toDtoUserResponse(user);
    }

    @Override
    public List<DtoUserResponse> findAllUsers(final int from, final int size, String sort) {
        sort = sort.toUpperCase();
        List<User> users = switch (sort) {
            case "ASC" -> userRepository.findAll(PageRequest.of(from, size, Sort.by("id"))).toList();
            case "DESC" -> userRepository.findAll(PageRequest.of(from, size, Sort.by("id").descending())).toList();
            default -> new ArrayList<>();
        };
        return userMapper.toDtoUserResponses(users);
    }

    @Override
    public DtoUserResponse setAccountStatus(final Long userId, final AccountStatus accountStatus) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        user.setAccountStatus(accountStatus);
        return userMapper.toDtoUserResponse(user);
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
}
