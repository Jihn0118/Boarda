package site.gongtong.alarm.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import site.gongtong.alarm.model.Alarm;

import java.util.List;

public interface AlarmService {
    SseEmitter subscribe(int usernum);
    Integer readAlarm(Integer id, Integer userNum);
    List<Alarm> getAlarmList(int userNum);
    Integer getCount(int userNum);
    Integer alarmMessage(int userNum);
}
