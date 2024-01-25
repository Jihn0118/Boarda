package site.gongtong.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.gongtong.member.model.Member;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    //34강
//    Optional<Member> findById(String id); //아이디 중복 확인, 권한 인증
    List<Member> findById(String id); //아이디 중복 확인, 권한 인증
//    boolean findByNickname(String nickname); //닉네임 중복 확인
//    int findByBrith(String birth); //연령대 별 추천에 필요

}