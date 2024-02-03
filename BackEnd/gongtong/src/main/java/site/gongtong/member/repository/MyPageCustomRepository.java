package site.gongtong.member.repository;


import site.gongtong.member.dto.EditProfileDto;
import site.gongtong.member.model.Member;
import site.gongtong.review.model.Review;

import java.util.List;

public interface MyPageCustomRepository {
    int MemberidToNum(String user_id);

    List<Review> findAllReviews(int user_num);

    Member findById(String id);

    Long modifyProfile(Member member);

    int modifyPwd(String id, String newEncodedPwd);

    int delete(String id);
}
