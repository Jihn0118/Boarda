package site.gongtong.review.repository;

import site.gongtong.review.model.Review;

public interface ReviewCustomRepository {
    Review findById(int reviewId);

    Long deleteReview(int reviewId);

}
