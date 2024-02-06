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
    public List<Moim> findByLocationAndStatusOrderByIdDesc(String location, Character status) {
        QMoim moim = QMoim.moim;

        return jpaQueryFactory
                .selectFrom(moim)
                .where(moim.location.eq(location), moim.status.eq(status))
                .orderBy(moim.id.desc())
                .fetch();
    }

    @Override
    public List<Moim> findByLocationAndStatusOrderByDatetime(String location, Character status) {
        QMoim moim = QMoim.moim;

        return jpaQueryFactory
                .selectFrom(moim)
                .where(moim.location.eq(location), moim.status.eq(status))
                .orderBy(moim.datetime.asc())
                .fetch();
    }

    @Override
    public List<Moim> getMoimWithMemberCountOrder() {
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
                        moim.status.eq('P')
                )
                .orderBy(moim.number.subtract(memberCount).asc())
                .fetch();
    }
}
