package site.gongtong.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.gongtong.member.model.Member;
import site.gongtong.moim.model.Moim;
import site.gongtong.review.model.Review;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDto {
    Member member;
    List<Review> reviews;
    Moim moim; //현재 참가한 모임, status가 P인 것만
    List<Moim> moimList;
    long followerCount;
    long followingCount;
}
