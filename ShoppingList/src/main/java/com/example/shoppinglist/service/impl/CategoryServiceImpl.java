package com.example.shoppinglist.service.impl;

import com.example.shoppinglist.model.entity.Category;
import com.example.shoppinglist.model.entity.CategoryEnum;
import com.example.shoppinglist.repository.CategoryRepository;
import com.example.shoppinglist.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryEnum.values())
                    .forEach(categoryName -> {
                        Category category = new Category();
                        category.setName(categoryName);
                               category.setDescription("Description for category " + categoryName);
                        categoryRepository.save(category);
                    });
        }
    }

    @Override
    public Category findByCategoryEnumName(CategoryEnum category) {
        return categoryRepository.findByName(category).orElse(null);
    }
}
