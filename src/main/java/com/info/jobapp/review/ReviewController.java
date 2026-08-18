package com.info.jobapp.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies/{companyId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(reviewService.getAllReviews(companyId));
    }
    @GetMapping("/{reviewId}")
    public ResponseEntity<?> getReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getReview(companyId, reviewId);
        if (review != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(review);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found");
    }

    @PostMapping
    public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean reviewSaved =  reviewService.addReview(companyId,review);
        if(reviewSaved){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Review added successfully");
        }
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("No such company exists");
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review) {

        boolean isReviewUpdated =  reviewService.updateReview(companyId,reviewId,review);
        if(isReviewUpdated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Review updated successfully");
        }
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Something went wrong. Please Check if both Company and Review Exists");
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean isReviewDeleted = reviewService.deleteReview(companyId, reviewId);
        if (isReviewDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Review deleted successfully !");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found");
    }
}