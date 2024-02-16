package site.gongtong.moim.repository;

import org.springframework.data.repository.query.Param;
import site.gongtong.member.model.Member;
import site.gongtong.moim.model.Moim;
import site.gongtong.moim.model.MoimGroup;
import site.gongtong.moim.model.MoimMember;

import java.util.List;

public interface MoimMemberCustomRepository {
    Integer countMoimsByMemberIdAndStatus(@Param("memberId") String memberId);

    Integer countMoimMemberByMoimId(Integer moimId);

    MoimMember findMoimMemberByMoimAndMember(Moim moim, Member member);

    List<MoimMember> findMoimMembersByMoim(Moim moim);

    long deleteMoimMember(String memberId, int moimId);

    List<MoimGroup> findMoimGroupByMemberId(String memberId);
}
