package site.gongtong.moim.repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.gongtong.moim.model.Moim;
import site.gongtong.moim.model.QMoim;
import site.gongtong.moim.model.QMoimMember;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MoimCustomRepositoryImpl implements MoimCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Moim findById(int moimId) {
        QMoim moim = QMoim.moim;

        return jpaQueryFactory.selectFrom(moim)
                .where(moim.id.eq(moimId))
                .fetchOne();
    }

    @Override
    public List<Moim> findAllDeadLine() {
        QMoim moim = QMoim.moim;
        QMoimMember moimMember = QMoimMember.moimMember;

        return jpaQueryFactory
                .selectFrom(moim)
                .where(moim.number.subtract(
                        jpaQueryFactory.select(moimMember.count())
                                .from(moimMember)
                                .where(moimMember.moim.eq(moim))
                ).eq(1))
                .fetch();
    }

    @Override
    public List<Moim> findByLocationAndStatusOrderByIdDesc(String location) {
        QMoim moim = QMoim.moim;

        return jpaQueryFactory
                .selectFrom(moim)
                .where(moim.location.eq(location), moim.status.eq('P'))
                .orderBy(moim.id.desc())
                .fetch();
    }

    @Override
    public List<Moim> findByLocationAndStatusOrderByDatetime(String location) {
        QMoim moim = QMoim.moim;

        return jpaQueryFactory
                .selectFrom(moim)
                .where(moim.location.eq(location), moim.status.eq('P'))
                .orderBy(moim.datetime.asc())
                .fetch();
    }

    public List<Moim> findByLocationAndStatusOrderByCount(String location) {
        QMoim moim = QMoim.moim;
        QMoimMember moimMember = QMoimMember.moimMember;

        NumberExpression<Integer> memberCount = Expressions.asNumber(
                jpaQueryFactory.select(moimMember.count())
                        .from(moimMember)
                        .where(moimMember.moim.eq(moim))
        ).intValue();

        return jpaQueryFactory
                .selectFrom(moim)
                .where(
                        moim.number.subtract(memberCount).goe(1),
                        moim.status.eq('P'),
                        moim.location.eq(location)
                )
                .orderBy(moim.number.subtract(memberCount).asc())
                .fetch();
    }

    @Override
    public List<Moim> findMoimListByMemberId(String memberId) {
        QMoim moim2 = QMoim.moim;
        QMoimMember moimMember2 = QMoimMember.moimMember;

        return jpaQueryFactory
                .selectFrom(moim2)
                .join(moimMember2)
                .on(moim2.id.eq(moimMember2.moim.id))
                .where(moimMember2.member.id.eq(memberId))
                .orderBy(moim2.datetime.desc())
                .fetch();
    }

    @Override
    public Moim findMoimByMemberId(String memberId) {
        QMoim qMoim = QMoim.moim;
        QMoimMember qMoimMember = QMoimMember.moimMember;

        return jpaQueryFactory.selectFrom(qMoimMember)
                .join(qMoimMember.moim, qMoim)
                .where(qMoimMember.member.id.eq(memberId)
                        .and(qMoim.status.eq('P')))
                .orderBy(qMoim.createdAt.desc()) //필드명 바꿈 create->created
                .fetchFirst()
                .getMoim();
    }

    @Override
    public long minusCurrentNumber(String memberId, int moimId, int num) {
        QMoim moim = QMoim.moim;
        return jpaQueryFactory.update(moim)
                .set(moim.currentNumber, num)
                .where(moim.id.eq(moimId))
                .execute();
    }

    @Override
    public long deleteMoim(int moimId) {
        QMoim moim = QMoim.moim;
        return jpaQueryFactory.delete(moim)
                .where(moim.id.eq(moimId))
                .execute();
    }
}
