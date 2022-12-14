package com.project.catalog.catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.catalog.catalog.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
