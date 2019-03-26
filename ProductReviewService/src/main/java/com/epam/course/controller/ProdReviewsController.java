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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.course.attributes.ProductCodes;
import com.epam.course.helper.ProdReviewsHelper;
import com.epam.course.model.ProdReviews;
import com.epam.course.service.ProdReviewsService;


@RestController
@RequestMapping("/api")
public class ProdReviewsController {
	
	@Autowired
	private transient ProdReviewsService prodService;
	
	
	@GetMapping("/prodReviews")
	public ResponseEntity<List<ProdReviews>> getProdReviews(@RequestHeader(value = "API_KEY") String secret)	{		
			
		if(!prodService.getProdReviews().isEmpty()) {
			return ResponseEntity.ok(prodService.getProdReviews());
		} else {			
			return ResponseEntity.ok(ProdReviewsHelper.buildErrMsg());
		}
		
	}
	
	@PostMapping("/prodReviews")
	public ResponseEntity<ProdReviews> saveProdReview(@RequestBody ProdReviews prodReviews,@RequestHeader(value = "API_KEY") String secret)
	{	
		return ResponseEntity.ok(prodService.saveProdProdReviews(prodReviews));
		
	}
	
	@GetMapping("/prodReviews/{Id}")
	public ResponseEntity<List<ProdReviews>> getProdReview(final @PathVariable long Id,@RequestHeader(value = "API_KEY") String secret)
	{
		 System.out.println("getProdReview inside ::"); 
		List<ProdReviews> prodReviews = prodService.getProdReview(Id);		
		 System.out.println("prod Reviews ::"+prodReviews.toString());
		if(prodReviews.isEmpty()) {	
			return ResponseEntity.ok(ProdReviewsHelper.buildErrMsg());
		} else {
			return ResponseEntity.ok(prodService.getProdReview(Id));
		}
		
	}
	
	@DeleteMapping("/prodReviews/{Id}")
	public ResponseEntity<?> removeProdReview(final @PathVariable long Id,@RequestHeader(value = "API_KEY") String secret)
	{
		Optional<ProdReviews> prodReviews = prodService.removeProdReview(Id);		
		if(!prodReviews.isPresent())			
			return ResponseEntity.ok(ProdReviewsHelper.buildErrMsg(Id, ProductCodes.PROD_NOT_DEL));
		else
			return ResponseEntity.ok(ProdReviewsHelper.buildErrMsg(Id, ProductCodes.PROD_DEL_SUCCESS));	
	}
	
	public ResponseEntity<List<ProdReviews>> getProdReview_Fallback(final @PathVariable long Id)
	{		
		return ResponseEntity.ok(ProdReviewsHelper.buildErrMsg());
	}
	
	@GetMapping("/reviews/{Id}")
	public List<ProdReviews> getReviews(final @PathVariable long Id)
	{
		 List<ProdReviews> prodReviews = prodService.getProdReview(Id);		
		 System.out.println(" Reviews ::"+prodReviews.toString());
		if(prodReviews.isEmpty()) {	
			return ProdReviewsHelper.buildErrMsg();
		} else {
			return prodService.getProdReview(Id);
		}
		
	}

}
