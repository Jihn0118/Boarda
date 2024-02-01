package site.gongtong.member.service;

import site.gongtong.review.model.Review;

import java.util.List;

public interface MyPageService {
    int idToNum(String id);
    List<Review> getReviewListByNum(int num);
}
