package com.example.battleships.service;

import com.example.battleships.model.binding.HomeBindingModel;
import com.example.battleships.model.service.ShipServiceModel;
import com.example.battleships.model.service.UserServiceModel;
import com.example.battleships.model.view.ShipViewModel;

import java.util.List;

public interface ShipService {
    void addShip(ShipServiceModel serviceModel, UserServiceModel currentUser);

    List<ShipViewModel> findAll();

    void fight(HomeBindingModel homeBindingModel);
}
