package com.example.shoppinglist.model.view;

import com.example.shoppinglist.model.entity.CategoryEnum;

import java.math.BigDecimal;

public class ProductViewModel {
    private Long id;
    private String name;
    private BigDecimal price;
    private CategoryEnum category;

    public ProductViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}
