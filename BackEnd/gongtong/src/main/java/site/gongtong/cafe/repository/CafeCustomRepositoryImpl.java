package site.gongtong.cafe.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.gongtong.Image.model.QImage;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.cafe.model.QCafe;
import site.gongtong.review.model.ImageReviewDto;
import site.gongtong.review.model.QReview;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CafeCustomRepositoryImpl implements CafeCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Cafe> findWithConditions(String location, String brand) {
        QCafe cafe = QCafe.cafe;

        BooleanBuilder builder = new BooleanBuilder();

        if (!location.isEmpty()) {
            builder.and(cafe.location.contains(location));
        }

        if (!brand.isEmpty()) {
            builder.and(cafe.brand.eq(brand));
        }

        return jpaQueryFactory
                .selectFrom(cafe)
                .where(builder)
                .orderBy(cafe.rate.desc())
                .fetch();
    }

    @Override
    public List<ImageReviewDto> getImagesAndReviewIdsByCafeId(Integer cafeId) {
        QImage image = QImage.image;
        QReview qReview = QReview.review;
        QCafe qCafe = QCafe.cafe;
        return jpaQueryFactory
                .select(Projections.constructor(ImageReviewDto.class, image, qReview))
                .from(qReview)
                .join(qReview.images, image)
                .join(qReview.cafe, qCafe)
                .where(qCafe.id.eq(cafeId))
                .fetch();
    }

    @Override
    public Cafe findById(int cafeId) {
        QCafe qCafe = QCafe.cafe;

        return jpaQueryFactory.selectFrom(qCafe)
                .where(qCafe.id.eq(cafeId))
                .fetchOne();
    }
}
