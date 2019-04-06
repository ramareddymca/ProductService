package com.epam.course.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.course.model.Product;

@Repository
public interface ProdRepository extends JpaRepository<Product, Long> {

}
