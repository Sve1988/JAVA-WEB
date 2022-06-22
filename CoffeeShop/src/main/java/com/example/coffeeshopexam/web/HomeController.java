package com.example.coffeeshopexam.web;

import com.example.coffeeshopexam.service.OrderService;
import com.example.coffeeshopexam.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final OrderService orderService;
    private final UserService userService;
    private final HttpSession httpSession;

    public HomeController(OrderService orderService, UserService userService, HttpSession httpSession) {
        this.orderService = orderService;
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @GetMapping("/")
    public String index(Model model) {



        if (httpSession.getAttribute("user") == null) {
            return "index";
        }

        model.addAttribute("orders",orderService.findAllOrdersOrderByPrice());
        model.addAttribute("totalTime", orderService.sumTotalTimeToOrder());
        model.addAttribute("users", userService.findAllUserAndCountOfOrders());

        return "home";
    }

}
