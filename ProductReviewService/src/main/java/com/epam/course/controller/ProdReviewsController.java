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
import com.epam.course.helper.ProdReviewsHelper;
import com.epam.course.model.ProdReviews;
import com.epam.course.service.ProdReviewsService;

@RestController
@RequestMapping("/api")
public class ProdReviewsController {
	
	@Autowired
	private transient ProdReviewsService prodService;
	
	
	@GetMapping("/prodReviews")
	public ResponseEntity<List<ProdReviews>> getProdReviews()	{
			
		if(!prodService.getProdReviews().isEmpty()) {
			return ResponseEntity.ok(prodService.getProdReviews());
		} else {			
			return ResponseEntity.ok(ProdReviewsHelper.buildErrMsg());
		}
		
	}
	
	@PostMapping("/prodReviews")
	public ResponseEntity<ProdReviews> saveProdReview(@RequestBody ProdReviews prodReviews)
	{	
		return ResponseEntity.ok(prodService.saveProdProdReviews(prodReviews));
		
	}
	
	@GetMapping("/prodReviews/{Id}")
	public ResponseEntity<List<ProdReviews>> getProdReview(final @PathVariable long Id)
	{
		 List<ProdReviews> prodReviews = prodService.getProdReview(Id);		
		if(prodReviews.isEmpty()) {	
			return ResponseEntity.ok(ProdReviewsHelper.buildErrMsg());
		} else {
			return ResponseEntity.ok(prodService.getProdReview(Id));
		}
		
	}
	
	@DeleteMapping("/prodReviews/{Id}")
	public ResponseEntity<?> removeProdReview(final @PathVariable long Id)
	{
		Optional<ProdReviews> prodReviews = prodService.removeProdReview(Id);		
		if(!prodReviews.isPresent())			
			return ResponseEntity.ok(ProdReviewsHelper.buildErrMsg(Id, ProductCodes.PROD_NOT_DEL));
		else
			return ResponseEntity.ok(ProdReviewsHelper.buildErrMsg(Id, ProductCodes.PROD_DEL_SUCCESS));	
	}

}
