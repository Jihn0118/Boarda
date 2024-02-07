package site.gongtong.alarm.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.gongtong.alarm.repository.AlarmRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService{
    private final AlarmRepository alarmRepository;

}
