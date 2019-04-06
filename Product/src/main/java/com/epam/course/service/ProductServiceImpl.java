package com.epam.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.course.model.Product;
import com.epam.course.repo.ProdRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProdRepository repo;

	@Override
	public List<Product> getProducts() {		
		return repo.findAll();
	}

	@Override
	public Product saveProduct(Product product) {
		repo.save(product);
		return product;
	}

	@Override
	public Optional<Product> getProduct(long id) {
		return repo.findById(id);
	}

}
