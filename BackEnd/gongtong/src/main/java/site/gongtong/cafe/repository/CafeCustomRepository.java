package site.gongtong.cafe.repository;

import site.gongtong.cafe.model.Cafe;
import site.gongtong.review.model.ImageReviewDto;

import java.util.List;

public interface CafeCustomRepository {
    List<Cafe> findWithConditions(String location, String brand);

    List<ImageReviewDto> getImagesAndReviewIdsByCafeId(Integer cafeId);

    Cafe findById(int cafeId);
}
