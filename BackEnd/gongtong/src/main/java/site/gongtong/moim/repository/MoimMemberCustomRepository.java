package site.gongtong.moim.repository;

import org.springframework.data.repository.query.Param;
import site.gongtong.member.model.Member;
import site.gongtong.moim.model.Moim;
import site.gongtong.moim.model.MoimMember;

public interface MoimMemberCustomRepository {
    Integer countMoimsByMemberIdAndStatus(@Param("memberId") int memberId);

    Integer countMoimMemberByMoimId(Integer moimId);

    MoimMember findMoimMemberByMoimAndMember(Moim moim, Member member);


}
