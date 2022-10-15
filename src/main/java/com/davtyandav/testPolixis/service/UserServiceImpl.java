package com.davtyandav.testPolixis.service;

import com.davtyandav.testPolixis.model.User;
import com.davtyandav.testPolixis.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public Optional<User> getUser(String id) {
        return userRepository.findById(id);
    }
}
