package site.gongtong.review.service;

import org.springframework.web.multipart.MultipartFile;
import site.gongtong.review.model.Review;
import site.gongtong.review.model.ReviewDto;

import java.util.List;

public interface ReviewService {

    List<Review> getReviews(String memberId);

    Long deleteReview(int reviewId, String memberId);

    Integer createReview(ReviewDto reviewDto, List<String> gameNameList, List<MultipartFile> files, String memberId);

    List<String> getGameNameList();
}
