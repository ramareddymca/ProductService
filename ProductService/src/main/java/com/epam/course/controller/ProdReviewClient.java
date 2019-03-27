package com.epam.course.controller;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.course.model.ProdReviews;

@FeignClient(name = "PRODUCTREVIEW-SERVICE",fallback = ProdReviewClientFallback.class)
public interface ProdReviewClient {

	@RequestMapping(value = "/api/prodReviews/{id}", method = RequestMethod.GET)
	public List<ProdReviews> getReviews(final @PathVariable("id") long Id,
			@RequestHeader(value = "API_KEY") String secret);

	@RequestMapping(value = "/api/prodReviews/", method = RequestMethod.POST)
	public ProdReviews saveProdReview(@RequestBody ProdReviews reviews,
			@RequestHeader(value = "API_KEY") String secret);

}
