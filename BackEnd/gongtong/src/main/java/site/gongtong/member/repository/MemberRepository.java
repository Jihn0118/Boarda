package site.gongtong.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.gongtong.member.model.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer>{
    //34강
    Optional<Member> findById(String id); //아이디 찾기(자동)
    Boolean existsByNickname(String nickname); //닉네임 중복 확인(자동)
    Boolean existsById(String id); //아이디 중복 확인(자동)

}