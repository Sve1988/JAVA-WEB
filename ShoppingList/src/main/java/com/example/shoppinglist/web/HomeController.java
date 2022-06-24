package com.example.shoppinglist.web;

import com.example.shoppinglist.model.entity.CategoryEnum;
import com.example.shoppinglist.service.ProductService;
import com.example.shoppinglist.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final ProductService productService;
    private final UserService userService;
    private final HttpSession httpSession;

    public HomeController(ProductService productService, UserService userService, HttpSession httpSession) {
        this.productService = productService;
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "index";
        }

        model.addAttribute("totalSum",productService.sumTotalProducts());
        model.addAttribute("drinks", productService.findAllByCategoryName(CategoryEnum.DRINK));
        model.addAttribute("foods", productService.findAllByCategoryName(CategoryEnum.FOOD));
        model.addAttribute("households", productService.findAllByCategoryName(CategoryEnum.HOUSEHOLD));
        model.addAttribute("others", productService.findAllByCategoryName(CategoryEnum.OTHER));

        return "home";
    }


}
