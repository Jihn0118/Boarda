package site.gongtong.moim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.gongtong.moim.model.Moim;
import site.gongtong.moim.model.MoimMember;

import java.util.List;

public interface MoimMemberRepository extends JpaRepository<MoimMember, Integer> {

    // 진행 중인 방 체크 Native Query
    //@Query(value = "SELECT count(*) FROM moim m JOIN moim_member mm ON m.id = mm.moim_id WHERE mm.member_id = :memberId AND m.status = 'P'", nativeQuery = true)
    // JPQL
    @Query("SELECT COUNT(mm) FROM moim_member mm WHERE mm.member.num = :memberId AND mm.moim.status = 'P'")
    Integer countMoimsByMemberIdAndStatus(@Param("memberId") int memberId);

    Integer countMoimMemberByMoim_Id(Integer moimId);
}
