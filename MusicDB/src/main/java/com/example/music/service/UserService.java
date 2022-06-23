package com.example.music.service;

import com.example.music.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    boolean loginUser(UserServiceModel userServiceModel);

    void logout();

    UserServiceModel findByUsername(String username);
}
