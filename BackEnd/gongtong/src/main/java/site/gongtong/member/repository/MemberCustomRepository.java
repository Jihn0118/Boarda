package site.gongtong.member.repository;

import site.gongtong.member.model.Member;

public interface MemberCustomRepository {
    Member findMemberByNum(int userNum);

    Member findById(String memberId);
}
