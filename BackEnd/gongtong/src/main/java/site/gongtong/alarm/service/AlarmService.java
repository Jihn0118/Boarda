package site.gongtong.alarm.service;

import site.gongtong.alarm.model.Alarm;

import java.util.List;

public interface AlarmService {

    List<Alarm> getAlarmList(int userNum);
}
