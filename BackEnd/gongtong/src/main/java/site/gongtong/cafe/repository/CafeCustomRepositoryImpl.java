package site.gongtong.cafe.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.cafe.model.QCafe;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CafeCustomRepositoryImpl implements CafeCustomRepository{
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
    public Cafe findCafeDetail(Integer cafeId) {
        QCafe cafe = QCafe.cafe;

        return jpaQueryFactory
                .selectFrom(cafe)
                .where(cafe.id.eq(cafeId))
                .fetchOne();
    }
}
