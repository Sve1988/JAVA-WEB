package com.example.coffeeshopexam.service;

import com.example.coffeeshopexam.model.service.UserServiceModel;
import com.example.coffeeshopexam.model.view.UserViewModel;

import java.util.List;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    boolean loginUser(UserServiceModel userServiceModel);

    void logout();

    UserServiceModel findByUsername(String username);

    List<UserViewModel> findAllUserAndCountOfOrders();
}
