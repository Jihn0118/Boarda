package site.gongtong.cafe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.cafe.model.CafeDetailDto;
import site.gongtong.cafe.repository.CafeCustomRepository;
import site.gongtong.cafe.repository.CafeRepository;
import site.gongtong.review.model.ImageReviewDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeServiceImpl implements CafeService {
    private final CafeRepository cafeRepository;
    private final CafeCustomRepository cafeCustomRepository;

    @Override
    public List<Cafe> getCafeList(String location, String brand) {
        return cafeCustomRepository.findWithConditions(location, brand);
    }

    @Override
    public CafeDetailDto getCafeInfo(Integer cafeId) {
        Cafe cafe = cafeCustomRepository.findById(cafeId);
        List<ImageReviewDto> imageReviewDtos = cafeCustomRepository.getImagesAndReviewIdsByCafeId(cafeId);
        return CafeDetailDto.builder()
                .cafe(cafe)
                .imageReviews(imageReviewDtos)
                .build();
    }
}
