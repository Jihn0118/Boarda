package site.gongtong.cafe.model;

import lombok.*;
import site.gongtong.review.model.ImageReviewDto;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CafeDetailDto {
    private Cafe cafe;
    private List<ImageReviewDto> imageReviews;
}
