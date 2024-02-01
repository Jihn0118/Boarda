package site.gongtong.member.repository;


import site.gongtong.review.model.Review;

import java.util.List;

public interface MyPageCustomRepository {
    int MemberidToNum(String user_id);

    List<Review> findAllReviews(int user_num);
}
