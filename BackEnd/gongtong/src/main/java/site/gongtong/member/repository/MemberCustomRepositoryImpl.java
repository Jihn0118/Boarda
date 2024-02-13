package site.gongtong.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.gongtong.member.model.Member;
import site.gongtong.member.model.QMember;

@Repository
@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Member findMemberById(String memberId) {
        QMember qMember = QMember.member;

        return jpaQueryFactory.selectFrom(qMember)
                .where(qMember.id.eq(memberId))
                .fetchOne();
    }

    @Override
    public Member findById(String memberId) {
        QMember member = QMember.member;

        return jpaQueryFactory.selectFrom(member)
                .where(member.id.eq(memberId))
                .fetchOne();
    }
}
