package site.gongtong.alarm.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import site.gongtong.alarm.model.Alarm;

import java.util.List;

public interface AlarmService {
    SseEmitter subscribe(String memberId);

    Integer readAlarm(Integer id, String memberId);

    List<Alarm> getAlarmList(String memberId);

    Long getCount(String memberId);

    Integer alarmMessage(String memberId);

    long getLatestAlarm(String memberId);
}
