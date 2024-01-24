package site.gongtong.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.gongtong.member.model.Member;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
//    boolean existByLoginId(String id);
//    boolean existByNickname(String nickname);
    Optional<Member> findByLoginId(String id);
}