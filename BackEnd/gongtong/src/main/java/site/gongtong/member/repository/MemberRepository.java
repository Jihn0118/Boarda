package site.gongtong.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.gongtong.member.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
    Member findMemberByNum(int num);
    Member findMemberById(String userId);
}
