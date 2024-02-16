package site.gongtong.alarm.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import site.gongtong.alarm.model.Alarm;
import site.gongtong.alarm.service.AlarmService;
import site.gongtong.security.jwt.TokenUtils;

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
    public static Map<String, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(HttpServletRequest request) {
        log.info("SSE 구독 컨트롤러 들어옴!!!");

        String memberId = TokenUtils.getUserIdFromToken(TokenUtils.fetchToken(request));

        return alarmService.subscribe(memberId);
    }

    @GetMapping("/read")
    public ResponseEntity<Integer> readAlarm(@RequestParam("alarm_id") Integer id,
                                             HttpServletRequest request) {

        log.info("알림 읽기");

        String memberId = TokenUtils.getUserIdFromToken(TokenUtils.fetchToken(request));

        int result = alarmService.readAlarm(id, memberId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Alarm>> getAlarmList(HttpServletRequest request) {
        log.info("내 알람 리스트 가져오기");
        String memberId = TokenUtils.getUserIdFromToken(TokenUtils.fetchToken(request));

        List<Alarm> alarms = alarmService.getAlarmList(memberId);

        return new ResponseEntity<>(alarms, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getAlarmCount(HttpServletRequest request) {

        log.info("알람 카운트 받아오기");
        String memberId = TokenUtils.getUserIdFromToken(TokenUtils.fetchToken(request));

        Long count = alarmService.getCount(memberId);

        if (count == null) {
            return new ResponseEntity<>(0, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(count.intValue(), HttpStatus.OK);
        }
    }

    @GetMapping("/start")
    public ResponseEntity<Long> getLatestAlarm(HttpServletRequest request) {
        log.info("로그인 후 최신 알람 받아오기");
        String memberId = TokenUtils.getUserIdFromToken(TokenUtils.fetchToken(request));

        long result = alarmService.getLatestAlarm(memberId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
