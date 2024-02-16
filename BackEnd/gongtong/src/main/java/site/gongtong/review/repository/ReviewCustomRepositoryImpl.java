package site.gongtong.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.gongtong.Image.model.QImage;
import site.gongtong.review.model.QReview;
import site.gongtong.review.model.Review;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {
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
    public Long deleteReview(int reviewId, String memberId) {
        QReview removedReview = QReview.review;

        return jpaQueryFactory
                .update(removedReview)
                .set(removedReview.isRemoved, true)
                .where(removedReview.member.id.eq(memberId),
                        removedReview.id.eq(reviewId),
                        removedReview.isRemoved.eq(false))
                .execute();
    }

    @Override
    public List<Review> findReviewsWithImagesByUserId(String memberId) {
        QReview review = QReview.review;
        QImage image = QImage.image;

        return jpaQueryFactory.select(review)
                .from(review)
                .leftJoin(review.images, image).fetchJoin()
                .where(review.member.id.eq(memberId))
                .orderBy(review.createdAt.desc())
                .fetch();
    }
}
