package com.example.shoppinglist.service;

import com.example.shoppinglist.model.entity.CategoryEnum;
import com.example.shoppinglist.model.service.ProductServiceModel;
import com.example.shoppinglist.model.service.UserServiceModel;
import com.example.shoppinglist.model.view.ProductViewModel;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void addProduct(ProductServiceModel serviceModel, UserServiceModel currentUser);

    BigDecimal sumTotalProducts();

    List<ProductViewModel> findAllByCategoryName(CategoryEnum category);

    void buyProduct(Long id);

    void buyAll();
}
