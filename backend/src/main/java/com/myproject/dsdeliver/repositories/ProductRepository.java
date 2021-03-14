package com.myproject.dsdeliver.repositories;

import java.util.List;

import com.myproject.dsdeliver.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByNameAsc();
}
