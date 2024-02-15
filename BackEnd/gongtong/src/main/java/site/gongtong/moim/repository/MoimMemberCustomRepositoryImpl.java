package site.gongtong.moim.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.gongtong.member.model.Member;
import site.gongtong.member.model.QMember;
import site.gongtong.moim.model.*;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MoimMemberCustomRepositoryImpl implements MoimMemberCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Integer countMoimsByMemberIdAndStatus(String memberId) {
        QMoimMember moimMember = QMoimMember.moimMember;

        return (int) (long) jpaQueryFactory
                .select(moimMember.count())
                .from(moimMember)
                .where(moimMember.member.id.eq(memberId), moimMember.moim.status.eq('P'))
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

    @Override
    public List<MoimMember> findMoimMembersByMoim(Moim moim) {
        QMoimMember moimMember = QMoimMember.moimMember;

        return jpaQueryFactory.selectFrom(moimMember)
                .where(moimMember.moim.eq(moim))
                .fetch();
    }

    @Override
    public long deleteMoimMember(String memberId, int moimId) {
        QMoimMember moimMember = QMoimMember.moimMember;

        return jpaQueryFactory.delete(moimMember)
                .where(moimMember.moim.id.eq(moimId).and(moimMember.member.id.eq(memberId)))
                .execute();

    }

    @Override
    public List<MoimGroup> findMoimGroupByMemberId(String memberId) {
        QMoim qMoim = QMoim.moim;
        QMoimMember qMoimMember = QMoimMember.moimMember;
        QMember qMember = QMember.member;

        List<Moim> moims = jpaQueryFactory
                .select(qMoim)
                .from(qMoim)
                .leftJoin(qMoimMember).on(qMoim.id.eq(qMoimMember.moim.id))
                .leftJoin(qMember).on(qMoimMember.member.num.eq(qMember.num))
                .where(qMember.id.eq(memberId))
                .fetch();

        // 각 모임에 참여한 모든 멤버 목록을 모임별로 조회
        return moims.stream()
                .map(moim -> {
                    List<Member> members = jpaQueryFactory
                            .select(qMember)
                            .from(qMember)
                            .leftJoin(qMoimMember).on(qMember.num.eq(qMoimMember.member.num))
                            .where(qMoimMember.moim.id.eq(moim.getId()))
                            .fetch();
                    return new MoimGroup(moim, members);
                })
                .collect(Collectors.toList());
    }
}
