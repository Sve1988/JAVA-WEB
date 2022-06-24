package com.example.shoppinglist.service.impl;

import com.example.shoppinglist.model.entity.CategoryEnum;
import com.example.shoppinglist.model.entity.Product;
import com.example.shoppinglist.model.entity.User;
import com.example.shoppinglist.model.service.ProductServiceModel;
import com.example.shoppinglist.model.service.UserServiceModel;
import com.example.shoppinglist.model.view.ProductViewModel;
import com.example.shoppinglist.repository.ProductRepository;
import com.example.shoppinglist.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addProduct(ProductServiceModel serviceModel, UserServiceModel currentUser) {
        Product product = modelMapper.map(serviceModel, Product.class);
      //  product.setEmployee(modelMapper.map(currentUser, User.class));
        productRepository.save(product);
    }

    @Override
    public BigDecimal sumTotalProducts() {
        return productRepository.findTotalProductSum();
    }

    @Override
    public List<ProductViewModel> findAllByCategoryName(CategoryEnum category) {
        return this.productRepository
                .findAllByCategory_Name(category)
                .stream()
                .map(product -> modelMapper.map(product, ProductViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void buyProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void buyAll() {
        productRepository.deleteAll();
    }
}
