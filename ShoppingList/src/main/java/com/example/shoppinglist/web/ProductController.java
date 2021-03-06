package com.example.shoppinglist.web;

import com.example.shoppinglist.model.binding.ProductAddBindingModel;
import com.example.shoppinglist.model.entity.Category;
import com.example.shoppinglist.model.service.ProductServiceModel;
import com.example.shoppinglist.model.service.UserServiceModel;
import com.example.shoppinglist.service.CategoryService;
import com.example.shoppinglist.service.ProductService;
import com.example.shoppinglist.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    public ProductController(ProductService productService, CategoryService categoryService, UserService userService, ModelMapper modelMapper, HttpSession httpSession) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.httpSession = httpSession;
    }

    @GetMapping("/add")
    public String addOrder() {
        return "product-add";
    }

    @PostMapping("/add")
    public String confirmAdd(@Valid ProductAddBindingModel productAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel",
                            bindingResult);
            return "redirect:add";
        }

        ProductServiceModel serviceModel = modelMapper
                .map(productAddBindingModel, ProductServiceModel.class);

        Category category = categoryService
                .findByCategoryEnumName(productAddBindingModel.getCategory());
        serviceModel.setCategory(category);

        UserServiceModel currentUser = modelMapper
                .map(httpSession
                        .getAttribute("user"), UserServiceModel.class);

        productService.addProduct(serviceModel, userService
                .findByUsername(currentUser.getUsername()));

        return "redirect:/";
    }

    @GetMapping("/buy/{id}")
    public String buyProduct(@PathVariable Long id){
        productService.buyProduct(id);
        return "redirect:/";
    }

    @GetMapping("/buy/all")
    public String buyAll(){
        productService.buyAll();
        return "redirect:/";
    }

    @ModelAttribute
    public ProductAddBindingModel productAddBindingModel() {
        return new ProductAddBindingModel();
    }
}
