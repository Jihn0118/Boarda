package site.gongtong.alarm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import site.gongtong.alarm.model.Alarm;
import site.gongtong.alarm.service.AlarmService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alarm")
@Slf4j
public class AlarmController {
    private final AlarmService alarmService;
    // 여러 클라이언트가 동시에 구독하고 데이터를 전송할 수 있으므로, 동시성 제어
    // thread-safe한 concurrentHashMap 사용
    public static Map<Integer, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@RequestParam("user_num")int userNum){
        // TODO @AuthenticationPrincipal 유저디테일로 유저 정보 꺼내서 하는 걸로 바꿔야 함
        SseEmitter sseEmitter = alarmService.subscribe(userNum);
        return sseEmitter;
    }

    @GetMapping("/read")
    public ResponseEntity<Integer> readAlarm(@RequestParam("alarm_id") Integer id, @RequestParam("user_num") Integer userNum) throws IOException {
        int result = alarmService.readAlarm(id, userNum);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Alarm>> getAlarmList(@RequestParam(name="num") int userNum){
        List<Alarm> alarms = alarmService.getAlarmList(userNum);

        return new ResponseEntity<>(alarms, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getAlarmCount(@RequestParam(name="num") int userNum){
        Integer count = alarmService.getCount(userNum);

        return new ResponseEntity<>(count, HttpStatus.OK);
    }

}
