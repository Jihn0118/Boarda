package site.gongtong.member.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import site.gongtong.member.model.Member;
import site.gongtong.review.model.Review;

import java.util.List;

public interface MyPageCustomRepository {
    Integer MemberidToNum(String user_id);

    Member findById(String id);

    Member findByNickname(String nickname);

    Long modifyProfile(Member member);

    Integer modifyPwd(String id, String newEncodedPwd);

    Integer delete(String id);

}
