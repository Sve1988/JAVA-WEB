package com.example.battleships.service;

import com.example.battleships.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    boolean loginUser(UserServiceModel userServiceModel);

    void logout();

    UserServiceModel findByUsername(String username);

}
