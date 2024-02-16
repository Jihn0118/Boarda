package site.gongtong.review.model;

import lombok.*;
import site.gongtong.Image.model.Image;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
public class ImageReviewDto {
    private Image image;
    private Review review;

    public ImageReviewDto(Image image, Review review) {
        this.image = image;
        this.review = review;
    }
}
