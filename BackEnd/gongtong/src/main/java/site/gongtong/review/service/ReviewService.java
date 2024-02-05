package site.gongtong.review.service;

import org.springframework.web.multipart.MultipartFile;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.review.model.Review;
import site.gongtong.review.model.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<Cafe> getCafeList();

    List<Review> getReviews(int userNum);

    Long deleteReview(int reviewId, int userNum);

    Integer createReview(ReviewDto reviewDto, List<MultipartFile> files);
}
