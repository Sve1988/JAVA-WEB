package com.example.shoppinglist.service;

import com.example.shoppinglist.model.entity.Category;
import com.example.shoppinglist.model.entity.CategoryEnum;

public interface CategoryService {
    void initCategories();

    Category findByCategoryEnumName(CategoryEnum category);
}
