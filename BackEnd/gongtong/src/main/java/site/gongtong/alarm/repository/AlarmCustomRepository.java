package site.gongtong.alarm.repository;

import site.gongtong.alarm.model.Alarm;

import java.util.List;

public interface AlarmCustomRepository {
    Alarm findFirstByRecieverOrderByCreatedAtDesc(String memberId);

    Alarm findById(Integer id);

    List<Alarm> findAllByMember(String memberId);

    Long getAlarmCount(String memberId);
}
