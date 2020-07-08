package com.tcrprojectubs.service;
import com.tcrprojectubs.domain.User;
import com.tcrprojectubs.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> list() {

        return userRepository.findAll();
    }

    public User save(User user) {

        return userRepository.save(user);
    }

    public void save(List<User> users) {

        userRepository.saveAll(users);
    }
}