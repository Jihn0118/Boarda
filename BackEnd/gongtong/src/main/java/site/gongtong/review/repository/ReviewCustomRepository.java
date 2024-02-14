package site.gongtong.review.repository;

import site.gongtong.review.model.Review;

import java.util.List;

public interface ReviewCustomRepository {
    Review findById(int reviewId);

    Long deleteReview(int reviewId, String memberId);

    List<Review> findReviewsWithImagesByUserId(String memberId);
}
