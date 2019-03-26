package com.epam.course.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.epam.course.attributes.ProductCodes;
import com.epam.course.exception.ProductNotFoundException;
import com.epam.course.model.ProdReviews;
import com.epam.course.model.Product;

public class ProductHelper {

	public static Optional<Product> buildErrMsg(String errMsg) {
		return Optional.of(new ProductNotFoundException().buildErr(errMsg));
	}

	public static Optional<Product> buildErrMsg(long prodId, String errMsg) {

		return Optional.of(new ProductNotFoundException().buildErr(prodId, errMsg));
	}

	public static List<Product> buildErrMsg() {	
		List<Product> noData = new ArrayList<Product>(1);
		noData.add(new ProductNotFoundException().buildErr(ProductCodes.PRODS_NOT_AVAIL));
		return noData;
	}
	
	public static List<Product> fallBackErrMsg() {	
		List<Product> noData = new ArrayList<Product>(1);
		noData.add(new ProductNotFoundException().buildErr(ProductCodes.SERVICE_DOWN));
		return noData;
	}
	
	public static Product fallBackProdReviewErrMsg() {
		Product product = new Product();
		product.setDesc(ProductCodes.SERVICE_DOWN);
		return product;
	}
	
	public static List<ProdReviews> fallBackReviewsErrMsg() {	
		List<ProdReviews> noData = new ArrayList<ProdReviews>(1);
		ProdReviews review = new ProdReviews();
		review.setComments(ProductCodes.SERVICE_DOWN);
		noData.add(review);	
		return noData;
	}

}
