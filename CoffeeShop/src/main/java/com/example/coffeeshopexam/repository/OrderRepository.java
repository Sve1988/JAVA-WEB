package com.example.coffeeshopexam.repository;

import com.example.coffeeshopexam.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o FROM Order o ORDER BY o.price desc ")
    List<Order> findAllByOrderByPriceDesc();

}
