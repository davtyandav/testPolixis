package com.davtyandav.testPolixis.service;

import com.davtyandav.testPolixis.model.User;
import com.davtyandav.testPolixis.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> getUser(String id) {
        return userRepository.findById(id);
    }
}
