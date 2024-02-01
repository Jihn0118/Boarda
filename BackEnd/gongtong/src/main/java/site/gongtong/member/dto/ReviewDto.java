package site.gongtong.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.gongtong.member.model.Member;
import site.gongtong.review.model.Review;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {
    Member member;
    List<Review> reviews;
}
