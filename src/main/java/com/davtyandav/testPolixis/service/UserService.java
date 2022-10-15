package com.davtyandav.testPolixis.service;

import com.davtyandav.testPolixis.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> getUser(String id);
}
