package site.gongtong.alarm.repository;

import site.gongtong.alarm.model.Alarm;

import java.util.List;

public interface AlarmCustomRepository {
    Alarm findFirstByRecieverOrderByCreatedAtDesc(int userNum);

    Alarm findById(Integer id);

    List<Alarm> findAllByMember(int userNum);
}
