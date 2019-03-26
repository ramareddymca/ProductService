package com.epam.course.controller;

import java.util.List;

import org.springframework.stereotype.Component;

import com.epam.course.helper.ProductHelper;
import com.epam.course.model.ProdReviews;

@Component
public class ProdReviewClientFallback implements ProdReviewClient {

	@Override
	public List<ProdReviews> getReviews(long Id, String secret) {
		return ProductHelper.fallBackReviewsErrMsg();
	}

	@Override
	public ProdReviews saveProdReview(ProdReviews reviews, String secret) {				
		return ProductHelper.fallBackReviewsErrMsg().get(0);
	}

}
