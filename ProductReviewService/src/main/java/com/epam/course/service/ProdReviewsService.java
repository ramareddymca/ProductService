/**
 * 
 */
package com.epam.course.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.epam.course.model.ProdReviews;

/**
 * @author Rama_Karri
 *
 */
@Service
public interface ProdReviewsService {
	
	public List<ProdReviews> getProdReviews();
	public ProdReviews saveProdProdReviews(ProdReviews prodReviews);
	public List<ProdReviews> getProdReview(long prodId);
	public Optional<ProdReviews> removeProdReview(long prodId);
	//public boolean removeMyProduct(long prodId);
	

}
