package site.gongtong.moim.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.gongtong.member.model.Member;
import site.gongtong.moim.model.Moim;
import site.gongtong.moim.model.MoimMember;
import site.gongtong.moim.model.QMoimMember;

@Repository
@RequiredArgsConstructor
public class MoimMemberCustomRepositoryImpl implements MoimMemberCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Integer countMoimsByMemberIdAndStatus(int memberId) {
        QMoimMember moimMember = QMoimMember.moimMember;

        return (int) (long) jpaQueryFactory
                .select(moimMember.count())
                .from(moimMember)
                .where(moimMember.member.num.eq(memberId), moimMember.moim.status.eq('P'))
                .fetchOne();
    }

    @Override
    public Integer countMoimMemberByMoimId(Integer moimId) {
        QMoimMember moimMember = QMoimMember.moimMember;

        return (int) (long) jpaQueryFactory
                .select(moimMember.count())
                .from(moimMember)
                .where(moimMember.moim.id.eq(moimId))
                .fetchOne();
    }

    @Override
    public MoimMember findMoimMemberByMoimAndMember(Moim moim, Member member) {
        QMoimMember moimMember = QMoimMember.moimMember;

        return jpaQueryFactory
                .selectFrom(moimMember)
                .where(moimMember.moim.eq(moim), moimMember.member.eq(member))
                .fetchOne();
    }
}
