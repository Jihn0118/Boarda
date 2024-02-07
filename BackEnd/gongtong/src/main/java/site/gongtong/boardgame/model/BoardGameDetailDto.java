package site.gongtong.boardgame.model;

import lombok.*;
import site.gongtong.review.model.ImageReviewDto;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardGameDetailDto {
    private BoardGame boardGame;
    private List<ImageReviewDto> imageReviews;
}
