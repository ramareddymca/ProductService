package com.epam.course;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.course.model.Product;
import com.epam.course.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	private static Logger log = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService prodService;
	
	
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return prodService.getProducts();
	}
	
	@GetMapping("/products/{id}")
	public Optional<Product> getAllProduct(@PathVariable long id) {
		return prodService.getProduct(id);
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> saveProducts(@RequestBody Product product) {
		return ResponseEntity.ok(prodService.saveProduct(product));
	}

}
