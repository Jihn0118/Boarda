package site.gongtong.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import site.gongtong.member.model.QFollow;

@Repository
public class FollowCustomRepositoryImpl implements FollowCustomRepository {
    private JPAQueryFactory jpaQueryFactory;

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
}
