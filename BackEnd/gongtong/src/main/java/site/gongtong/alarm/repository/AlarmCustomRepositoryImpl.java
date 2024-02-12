package site.gongtong.alarm.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import site.gongtong.alarm.model.Alarm;
import site.gongtong.alarm.model.QAlarm;
import site.gongtong.member.model.QMember;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AlarmCustomRepositoryImpl implements AlarmCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Alarm findFirstByRecieverOrderByCreatedAtDesc(int userNum) {
        QAlarm alarm = QAlarm.alarm;
        QMember member = QMember.member;
        return jpaQueryFactory
                .selectFrom(alarm)
                .join(alarm.member, member)
                .where(member.num.eq(userNum))
                .orderBy(alarm.createdAt.desc())
                .fetchFirst();
    }

    @Override
    public Alarm findById(Integer id) {
        QAlarm alarm = QAlarm.alarm;
        return jpaQueryFactory
                .selectFrom(alarm)
                .where(alarm.id.eq(id))
                .fetchOne();
    }

    @Override
    public List<Alarm> findAllByMember(int userNum) {
        QAlarm alarm = QAlarm.alarm;
        QMember member = QMember.member;
        return jpaQueryFactory
                .selectFrom(alarm)
                .join(alarm.member, member)
                .where(member.num.eq(userNum))
                .orderBy(alarm.createdAt.desc())
                .fetch();
    }
}
