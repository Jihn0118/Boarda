package site.gongtong.review.model;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReviewDto {
    private int userNum;    // 유저id
    private Float rate;     // 평점
    private String content; // 후기 내용
    private int cafeId;     // 카페id
    private int moimId;     // 모임id
}
