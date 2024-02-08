package site.gongtong.alarm.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.gongtong.alarm.model.Alarm;
import site.gongtong.alarm.repository.AlarmRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService{
    private final AlarmRepository alarmRepository;

    @Override
    public List<Alarm> getAlarmList(int userNum) {
        return null;
    }
}
