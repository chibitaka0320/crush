package com.example.crush.service;

import org.springframework.stereotype.Service;

import com.example.crush.domain.User;
import com.example.crush.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Integer id) {
        return userRepository.load(id);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public Integer insert(User user) {
        return userRepository.insert(user);
    }

    public void delete(Integer id) {
        userRepository.delete(id);
    }
}
