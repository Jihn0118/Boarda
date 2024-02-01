package site.gongtong.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.gongtong.member.model.QMember;
import site.gongtong.review.model.QReview;
import site.gongtong.review.model.Review;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyPageCustomRepositoryImpl implements MyPageCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public int MemberidToNum(String user_id) {
        QMember member = QMember.member;

        return jpaQueryFactory
                .select(member.num)
                .from(member)
                .where(member.id.eq(user_id))
                .fetchOne();
    }

    @Override
    public List<Review> findAllReviews(int user_num) {
        QReview review = QReview.review;

        return jpaQueryFactory
                .selectFrom(review)
                .where(review.memberId.eq(user_num))
                .fetch();
    }

//    @Override
//    public Moim findById(int moimId) {
//        QMoim moim = QMoim.moim;
//
//        return jpaQueryFactory.selectFrom(moim)
//                .where(moim.id.eq(moimId))
//                .fetchOne();
//    }

}
