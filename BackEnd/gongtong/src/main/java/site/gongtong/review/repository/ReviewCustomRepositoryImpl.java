package site.gongtong.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.gongtong.review.model.QReview;
import site.gongtong.review.model.Review;

@Repository
@RequiredArgsConstructor
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Review findById(int reviewId) {
        QReview review = QReview.review;

        return jpaQueryFactory.selectFrom(review)
                .where(review.id.eq(reviewId))
                .orderBy(review.createdAt.desc())
                .fetchOne();
    }

    @Override
    public Long deleteReview(int reviewId, int userNum) {
        QReview removedReview = QReview.review;

        return jpaQueryFactory
                .update(removedReview)
                .set(removedReview.isRemoved, true)
                .where(removedReview.member.num.eq(userNum),
                        removedReview.id.eq(reviewId),
                        removedReview.isRemoved.eq(false))
                .execute();
    }
}
