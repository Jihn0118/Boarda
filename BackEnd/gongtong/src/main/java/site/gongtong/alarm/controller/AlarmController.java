package site.gongtong.alarm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.gongtong.alarm.service.AlarmService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alarm")
@Slf4j
public class AlarmController {
    private final AlarmService alarmService;
}
