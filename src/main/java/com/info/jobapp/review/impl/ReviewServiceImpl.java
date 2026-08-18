package com.info.jobapp.review.impl;

import com.info.jobapp.company.Company;
import com.info.jobapp.company.CompanyService;
import com.info.jobapp.review.Review;
import com.info.jobapp.review.ReviewRepository;
import com.info.jobapp.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;


    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream().
                filter(review -> review.getId() == reviewId)
                .findFirst()
                .orElse(null);
    }


    @Override
    public boolean addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;

    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        Company company = companyService.getCompanyById(companyId);
        if (company == null)
            return false;

        updatedReview.setCompany(company);
        updatedReview.setId(reviewId);
        reviewRepository.save(updatedReview);

        return true;

    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if (companyService.getCompanyById(companyId) != null
                && reviewRepository.existsById(reviewId)) {
            Review review = reviewRepository.findById(reviewId).get();
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(companyId, company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}