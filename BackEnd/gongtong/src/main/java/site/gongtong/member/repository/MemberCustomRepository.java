package site.gongtong.member.repository;

import org.springframework.stereotype.Repository;
import site.gongtong.member.model.Member;

public interface MemberCustomRepository {
    Member findMemberByNum(int userNum);

    Member findById(String memberId);
}
