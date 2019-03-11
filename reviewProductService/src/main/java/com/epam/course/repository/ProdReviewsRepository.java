package com.epam.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.course.model.ProdReviews;

@Repository
public interface ProdReviewsRepository extends JpaRepository<ProdReviews,Long> {

}
