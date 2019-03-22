package com.epam.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.course.model.ProdReviews;
import com.epam.course.repository.ProdReviewsRepository;

@Service
public class ProdReviewsServiceImpl implements ProdReviewsService {
	@Autowired
	private ProdReviewsRepository reviewProdRepo;

	@Override
	public List<ProdReviews> getProdReviews() {

		return reviewProdRepo.findAll();
	}

	@Override
	public ProdReviews saveProdProdReviews(ProdReviews prodReviews) {
		reviewProdRepo.save(prodReviews);
		return prodReviews;
	}
	
	@Override
	public List<ProdReviews> getProdReview(long Id)
	{
		List<ProdReviews> prodReviews = reviewProdRepo.findByProdId(Id);
		return prodReviews;
	}
	
	@Override
	public Optional<ProdReviews> removeProdReview(long Id)
	{
		Optional<ProdReviews> prodReviews = reviewProdRepo.findById(Id);				
		return prodReviews;
	}

}
