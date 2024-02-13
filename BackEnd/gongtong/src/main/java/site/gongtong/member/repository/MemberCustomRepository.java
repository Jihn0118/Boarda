package site.gongtong.member.repository;

import site.gongtong.member.model.Member;

public interface MemberCustomRepository {
    Member findById(String memberId);

    Member findMemberById(String memberId);
}
