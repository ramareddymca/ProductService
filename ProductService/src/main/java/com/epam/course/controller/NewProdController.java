package com.epam.course.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.course.helper.ProductHelper;
import com.epam.course.model.ProdReviews;
import com.epam.course.model.Product;
import com.epam.course.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
@RequestMapping("/api2")
public class NewProdController {
	
	@Autowired
	private ProductService prodService;
	
	@Autowired
	private ProdReviewServiceClient feignProxy;
	
	@HystrixCommand(fallbackMethod = "getFeignProduct_fallback")
	@GetMapping("/products/{id}")
	public Optional<Product> getProduct(@PathVariable long id) {
		Optional<Product> product = prodService.getProduct(id);
		System.out.println("product info ::"+product.toString());
		// calling Product reviews thru Feign Client + Ribbon + Hystrix + API_KEY			
		System.out.println("before product review ");		
		List<ProdReviews> review = feignProxy.getReviews(id, "API_KEY");
		System.out.println("This Product review from feign -> "+review);
		product.get().setProdReviews(review);		
		return product;
	}
	
	@HystrixCommand(fallbackMethod = "saveFeignProducts_fallback")
	@PostMapping("products/{prodId}/reviews")
	public Product saveProducts(@RequestBody Product product,@PathVariable long prodId) {
		product.setProdId(prodId);	
		// calling Product reviews thru Feign Client + Ribbon + Hystrix + API_KEY
		ProdReviews review = feignProxy.saveProdReview(product.getProdReviews().get(0),"API_KEY");
		System.out.println("This Product review from feign -> "+review);
		return product;
	}
	
	public Optional<Product> getFeignProduct_fallback(@PathVariable long id) {
		List<Product> product = ProductHelper.fallBackErrMsg();	
		return Optional.of(product.get(0));
	}
	
	public Product saveFeignProducts_fallback(@RequestBody Product product,@PathVariable long prodId) {
		Product prod = ProductHelper.fallBackProdReviewErrMsg();		
		return prod;
	}

}
