package com.example.coffeeshopexam.service.Impl;

import com.example.coffeeshopexam.model.entity.Order;
import com.example.coffeeshopexam.model.entity.User;
import com.example.coffeeshopexam.model.service.OrderServiceModel;
import com.example.coffeeshopexam.model.service.UserServiceModel;
import com.example.coffeeshopexam.model.view.OrderViewModel;
import com.example.coffeeshopexam.repository.OrderRepository;
import com.example.coffeeshopexam.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addOrder(OrderServiceModel serviceModel, UserServiceModel currentUser) {
        Order order = modelMapper.map(serviceModel, Order.class);
        order.setEmployee(modelMapper.map(currentUser, User.class));
        orderRepository.save(order);
    }

    @Override
    public void readyToOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderViewModel> findAllOrdersOrderByPrice() {
        return orderRepository.findAllByOrderByPriceDesc()
                .stream()
                .map(o -> modelMapper.map(o, OrderViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Integer sumTotalTimeToOrder() {
        return orderRepository.findAll()
                .stream()
                .mapToInt(value -> value.getCategory().getNeededTime())
                .sum();
    }
}
