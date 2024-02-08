package site.gongtong.alarm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.gongtong.alarm.model.Alarm;
import site.gongtong.alarm.service.AlarmService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alarm")
@Slf4j
public class AlarmController {
    private final AlarmService alarmService;

    @GetMapping
    public ResponseEntity<List<Alarm>> getAlarmList(@RequestParam(name="num") int userNum){
        List<Alarm> alarms = alarmService.getAlarmList(userNum);

        return new ResponseEntity<>(alarms, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Void> checkAlarm(@RequestParam(name="user") int userNum, @RequestParam("alarm") int alarmId){

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
