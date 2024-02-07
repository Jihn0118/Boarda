package site.gongtong.alarm.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AlarmCustomRepositoryImpl implements AlarmCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;
}
