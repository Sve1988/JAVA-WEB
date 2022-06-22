package com.example.coffeeshopexam.service;

import com.example.coffeeshopexam.model.service.OrderServiceModel;
import com.example.coffeeshopexam.model.service.UserServiceModel;
import com.example.coffeeshopexam.model.view.OrderViewModel;

import java.util.List;

public interface OrderService {
    void addOrder(OrderServiceModel serviceModel, UserServiceModel currentUser);

    void readyToOrder(Long id);

    List<OrderViewModel> findAllOrdersOrderByPrice();

    Integer sumTotalTimeToOrder();
}
