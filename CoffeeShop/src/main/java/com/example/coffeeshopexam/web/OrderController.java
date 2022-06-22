package com.example.coffeeshopexam.web;

import com.example.coffeeshopexam.model.binding.OrderAddBindingModel;
import com.example.coffeeshopexam.model.entity.Category;
import com.example.coffeeshopexam.model.service.OrderServiceModel;
import com.example.coffeeshopexam.model.service.UserServiceModel;
import com.example.coffeeshopexam.service.CategoryService;
import com.example.coffeeshopexam.service.OrderService;
import com.example.coffeeshopexam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    public OrderController(OrderService orderService, CategoryService categoryService, UserService userService, ModelMapper modelMapper, HttpSession httpSession) {
        this.orderService = orderService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.httpSession = httpSession;
    }

    @GetMapping("/add")
    public String addOrder() {
        return "order-add";
    }

    @PostMapping("/add")
    public String confirmAdd(@Valid OrderAddBindingModel orderAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("orderAddBindingModel", orderAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModel",
                            bindingResult);
            return "redirect:add";
        }

        OrderServiceModel serviceModel = modelMapper
                .map(orderAddBindingModel, OrderServiceModel.class);

        Category category = categoryService
                .findByCategoryEnumName(orderAddBindingModel.getCategory());
        UserServiceModel currentUser = modelMapper
                .map(httpSession
                        .getAttribute("user"), UserServiceModel.class);

        serviceModel.setCategory(category);

        orderService.addOrder(serviceModel, userService
                .findByUsername(currentUser.getUsername()));

        return "redirect:/";
    }

    @GetMapping("/ready/{id}")
    public String ready(@PathVariable Long id) {
        orderService.readyToOrder(id);
        return "redirect:/";
    }

    @ModelAttribute
    public OrderAddBindingModel orderAddBindingModel() {
        return new OrderAddBindingModel();
    }
}

