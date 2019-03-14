package com.epam.course.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.epam.course.attributes.ProductCodes;
import com.epam.course.helper.ProductHelper;
import com.epam.course.model.ProdReviews;
import com.epam.course.model.Product;
import com.epam.course.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductsController {

	@Autowired
	private transient ProductService prodService;
	
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return prodService.getProducts();
	}

	@GetMapping("/products/{id}")
	public Resource<Product> getProduct(@PathVariable long id) {
		Optional<Product> product = prodService.getProduct(id);
		// calling Product reviews thru Rest template + API_KEY	
		HttpHeaders headers = new HttpHeaders();
        headers.set("API_KEY", "test");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		
		String prodReviewService_url ="http://localhost:8091/api/prodReviews/{id}";
		ResponseEntity<List<ProdReviews>> response = restTemplate.exchange(prodReviewService_url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<ProdReviews>>() {      }, id);
		List<ProdReviews> review = response.getBody();		
		System.out.println("This Product review -> "+review);
		product.get().setProdReviews(review);
		Resource<Product> resource = new Resource<Product>(product.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllProducts());
		resource.add(linkTo.withRel("all-products"));
		return resource;
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
	
	@PostMapping("/products/{prodId}/reviews")
	public ResponseEntity<Product> saveProducts(@RequestBody Product product,@PathVariable long prodId) {
		product.setProdId(prodId);	
		
		// calling Product reviews thru Rest template + API_KEY	
		HttpHeaders headers = new HttpHeaders();
        headers.set("API_KEY", "test");
        headers.setContentType(MediaType.APPLICATION_JSON);     	
        HttpEntity<ProdReviews> entity = new HttpEntity<ProdReviews>(product.getProdReviews().get(0),headers);
		String prodReviewService_url ="http://localhost:8091/api/prodReviews/";	
		restTemplate.postForObject(prodReviewService_url, entity, ProdReviews.class);			 
		return ResponseEntity.ok(prodService.saveProduct(product));
	}

}
