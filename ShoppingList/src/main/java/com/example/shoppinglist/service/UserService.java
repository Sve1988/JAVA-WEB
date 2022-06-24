package com.example.shoppinglist.service;

import com.example.shoppinglist.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    boolean loginUser(UserServiceModel userServiceModel);

    void logout();

    UserServiceModel findByUsername(String username);
}
