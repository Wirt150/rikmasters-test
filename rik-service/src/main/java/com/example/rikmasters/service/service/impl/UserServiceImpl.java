//package com.example.rikmasters.service.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.stereotype.Service;
//import ru.example.vitasoft.service.entity.User;
//import ru.example.vitasoft.service.exeption.UserNotFoundException;
//import ru.example.vitasoft.service.exeption.UsernameNotFoundException;
//import ru.example.vitasoft.service.repository.UserRepository;
//import ru.example.vitasoft.service.security.constant.Role;
//import ru.example.vitasoft.service.service.UserService;
//
//import javax.transaction.Transactional;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@Transactional
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//
//    @Override
//    public User findById(Long userId) {
//        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
//    }
//
//    @Override
//    public List<User> findAllUsers(int from, int size) {
//        return userRepository.findAll(PageRequest.of(from, size)).stream().collect(Collectors.toList());
//    }
//
//    @Override
//    public User findUserByName(final String username) {
//        return userRepository.findUserByUsernameContainingIgnoreCase(username).orElseThrow(() -> new UsernameNotFoundException(username));
//    }
//
//    @Override
//    public User setRole(final Long userId, final Role role) {
//        User user = this.findById(userId);
//        user.getRole().add(role);
//        return userRepository.save(user);
//    }
//}
