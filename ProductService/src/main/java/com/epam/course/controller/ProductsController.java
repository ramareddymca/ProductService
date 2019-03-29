package com.epam.course.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.course.attributes.ProductCodes;
import com.epam.course.helper.ProductHelper;
import com.epam.course.model.ProdReviews;
import com.epam.course.model.Product;
import com.epam.course.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductsController {

	@Autowired
	private ProductService prodService;

	@Autowired
	private ProdReviewClient feignProxy;

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return prodService.getProducts();
	}

	@GetMapping("/products/{id}")
	public Optional<Product> getProduct(@PathVariable long id) {
		Optional<Product> product = prodService.getProduct(id);
		System.out.println("product info ::" + product.toString());
		// calling Product reviews thru Feign Client 
		System.out.println("before product review ");
		List<ProdReviews> review = feignProxy.getReviews(id, "API_KEY");
		System.out.println("This Product review from feign -> " + review);
		product.get().setProdReviews(review);
		return product;
	}

	@PostMapping("products/{prodId}/reviews")
	public Product saveProducts(@RequestBody Product product, @PathVariable long prodId) {
		product.setProdId(prodId);
		// calling Product reviews thru Feign Client 
		ProdReviews review = feignProxy.saveProdReview(product.getProdReviews().get(0), "API_KEY");
		System.out.println("This Product review from feign -> " + review);
		return product;
	}

	@PostMapping("/products")
	public ResponseEntity<Product> saveProducts(@RequestBody Product product) {
		return ResponseEntity.ok(prodService.saveProduct(product));
	}

	@DeleteMapping("/products/{prodId}")
	public ResponseEntity<?> removeProduct(@PathVariable long prodId) {
		Optional<Product> product = prodService.removeProduct(prodId);
		if (!product.isPresent())
			return ResponseEntity.ok(ProductHelper.buildErrMsg(prodId, ProductCodes.PROD_NOT_DEL));
		else
			return ResponseEntity.ok(ProductHelper.buildErrMsg(prodId, ProductCodes.PROD_DEL_SUCCESS));
	}

}
