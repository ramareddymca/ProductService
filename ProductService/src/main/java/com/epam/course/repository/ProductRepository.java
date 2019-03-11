package com.epam.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.course.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
