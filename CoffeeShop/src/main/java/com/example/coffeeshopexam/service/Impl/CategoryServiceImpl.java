package com.example.coffeeshopexam.service.Impl;

import com.example.coffeeshopexam.model.entity.Category;
import com.example.coffeeshopexam.model.entity.CategoryEnum;
import com.example.coffeeshopexam.repository.CategoryRepository;
import com.example.coffeeshopexam.service.CategoryService;
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
            Arrays.stream(CategoryEnum.values()).forEach(categoryEnum -> {
                Category category = new Category();
                category.setName(categoryEnum);
                switch (categoryEnum) {
                    case DRINK -> category.setNeededTime(1);
                    case COFFEE -> category.setNeededTime(2);
                    case OTHER -> category.setNeededTime(5);
                    case CAKE -> category.setNeededTime(10);
                }
                categoryRepository.save(category);
            });
        }
    }

    @Override
    public Category findByCategoryEnumName(CategoryEnum category) {

        return categoryRepository.findByName(category).orElse(null);
    }
}
