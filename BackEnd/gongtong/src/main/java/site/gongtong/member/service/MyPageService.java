package site.gongtong.member.service;

import site.gongtong.member.dto.EditProfileDto;
import site.gongtong.member.model.Member;
import site.gongtong.review.model.Review;

import java.util.List;

public interface MyPageService {
    int idToNum(String id);

    List<Review> getReviewListByNum(int num);

    Member findById(String id);

    Member findByNickname(String nickname);

    Long modifyProfile(EditProfileDto editProfileDto);

    int setPwd(String id, String newEncodedPwd);

    int deleteMember(String id);
}
