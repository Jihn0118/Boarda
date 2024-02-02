package site.gongtong.review.service;

import site.gongtong.cafe.model.Cafe;
import site.gongtong.review.model.Review;
import site.gongtong.review.model.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<Cafe> getCafeList();

    List<Review> getReviews(int userNum);

    Long deleteReview(int reviewId);

    void createReview(ReviewDto reviewDto);
}
