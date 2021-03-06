package com.example.battleships.service.impl;

import com.example.battleships.model.entity.Category;
import com.example.battleships.model.entity.CategoryEnum;
import com.example.battleships.repository.CategoryRepository;
import com.example.battleships.service.CategoryService;
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
                    .forEach(categoryEnum -> {
                        Category category = new Category();
                        category.setName(categoryEnum);
                        category.setDescription("Description for " + categoryEnum.name().toLowerCase());
                        categoryRepository.save(category);
                    });
        }
    }

    @Override
    public Category findByCategoryEnumName(CategoryEnum category) {
        return categoryRepository.findByName(category).orElse(null);
    }
}
