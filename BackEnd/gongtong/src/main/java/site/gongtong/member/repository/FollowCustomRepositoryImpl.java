package site.gongtong.member.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import site.gongtong.member.model.Follow;
import site.gongtong.member.model.QFollow;
import site.gongtong.member.model.QMember;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FollowCustomRepositoryImpl implements FollowCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public FollowCustomRepositoryImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public int existRelation(int followerNum, int followingNum) {
        QFollow follow = QFollow.follow;

        return (int) jpaQueryFactory
                .select()
                .from(follow)
                .where(follow.follower.num.eq(followerNum)
                        .and(follow.following.num.eq(followingNum)))
                .fetchCount();
    }

    @Override
    public Follow findBy2Nums(int myNum, int yourNum) {
        QFollow follow = QFollow.follow;

        return jpaQueryFactory
                .selectFrom(follow)
                .where(follow.follower.num.eq(myNum).and(follow.following.num.eq(yourNum)))
                .fetchOne();
    }

    @Override
    public List<Tuple> findAllByNum(int userNum) {
        QMember member = QMember.member;
        QFollow follow = QFollow.follow;

        return jpaQueryFactory
                .select(member, follow)
                .from(member)
                .innerJoin(follow)
                .on(member.num.eq(follow.following.num))
                .where(follow.follower.num.eq(userNum))
                .fetch();
    }
}
