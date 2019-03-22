package com.epam.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.course.model.Product;
import com.epam.course.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository myProdRepo;

	@Override
	public List<Product> getProducts() {

		return myProdRepo.findAll();
	}

	@Override
	public Product saveProduct(Product product) {
		myProdRepo.save(product);
		return product;
	}
	
	@Override
	public Optional<Product> getProduct(long prodId)
	{
		Optional<Product> product = myProdRepo.findById(prodId);
		return product;
	}
	
	@Override
	public Optional<Product> removeProduct(long prodId)
	{
		Optional<Product> product = myProdRepo.findById(prodId);				
		return product;
	}

}
