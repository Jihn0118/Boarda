package site.gongtong.alarm.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import site.gongtong.alarm.controller.AlarmController;
import site.gongtong.alarm.model.Alarm;
import site.gongtong.alarm.repository.AlarmCustomRepository;
import site.gongtong.alarm.repository.AlarmRepository;
import site.gongtong.member.model.Member;
import site.gongtong.member.repository.MemberCustomRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class AlarmServiceImpl implements AlarmService {
    private final AlarmRepository alarmRepository;
    private final AlarmCustomRepository alarmCustomRepository;
    private final MemberCustomRepository memberCustomRepository;
    private static final Map<String, Long> alarmCounts = new HashMap<>();

    @Override
    public SseEmitter subscribe(String memberId) {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try {
            // SSE 연결
            // 첫 구독 시 이벤트를 보냄, 연결되었다는 응답으로 보내는 것, 안 보내면 503에러
            sseEmitter.send(SseEmitter.event().name("connect"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AlarmController.sseEmitters.put(memberId, sseEmitter);

        long cnt = getCount(memberId);

        alarmCounts.put(memberId, cnt);
        System.out.println("memberId" + memberId + "  " + alarmCounts.get(memberId));

        sseEmitter.onCompletion(() -> AlarmController.sseEmitters.remove(memberId));
        sseEmitter.onTimeout(() -> AlarmController.sseEmitters.remove(memberId));
        sseEmitter.onError((e) -> AlarmController.sseEmitters.remove(memberId));

        return sseEmitter;
    }

    @Override
    public Integer readAlarm(Integer id, String memberId) {
        Alarm alarm = alarmCustomRepository.findById(id);

        if (alarm == null) {
            return 1;
        }

        alarm.setIsRead(true);

        Alarm readAlarm = alarmRepository.save(alarm);

        if (alarmCounts.containsKey(memberId)) {
            long currentCount = alarmCounts.get(memberId);

            if (currentCount > 0) {
                alarmCounts.put(memberId, currentCount - 1);
            }
        }

        SseEmitter sseEmitter = AlarmController.sseEmitters.get(memberId);

        try {
            sseEmitter.send(SseEmitter.event().name("alarmCount").data(alarmCounts.get(memberId)));
        } catch (IOException e) {
            AlarmController.sseEmitters.remove(memberId);
            SseEmitter newEmitter = new SseEmitter(Long.MAX_VALUE);
            AlarmController.sseEmitters.put(memberId, newEmitter);
            return 3;
        }

        return 0;
    }

    @Override
    public List<Alarm> getAlarmList(String memberId) {
        return alarmCustomRepository.findAllByMember(memberId);
    }


    @Override
    public Integer alarmMessage(String memberId) {
        Member member = memberCustomRepository.findMemberById(memberId);

        if (member == null) {
            return 1;       // 해당 유저는 없음
        }
        Alarm recieveAlarm = alarmCustomRepository.findFirstByRecieverOrderByCreatedAtDesc(memberId);

        if (recieveAlarm == null) {
            return 2;       // 알림이 1개도 없음
        }

        if (AlarmController.sseEmitters.containsKey(memberId)) {
            SseEmitter sseEmitter = AlarmController.sseEmitters.get(memberId);
            try {
                Map<String, String> eventData = new HashMap<>();
                eventData.put("alarm", "알림이 왔습니다.");
                eventData.put("content", recieveAlarm.getContent());
                eventData.put("createdAt", recieveAlarm.getCreatedAt().toString());
                eventData.put("link", recieveAlarm.getLink());
                sseEmitter.send(SseEmitter.event().name("addAlarm").data(eventData));
            } catch (Exception e) {
                AlarmController.sseEmitters.remove(memberId);
            }
        }

        return 0;
    }

    @Override
    public long getLatestAlarm(String memberId) {
        Long count = getCount(memberId);

        SseEmitter sseEmitter = AlarmController.sseEmitters.get(memberId);

        if (count == null) {
            count = 0L;
        }

        try {
            Map<String, String> eventData = new HashMap<>();
            if (count == 0) {
                eventData.put("alarm", "미확인 알람이 없습니다.");
                eventData.put("count", "0");
            } else {
                eventData.put("alarm", "미확인 알람이 " + count + "개 있습니다");
                eventData.put("count", count.toString());
            }
            sseEmitter.send(SseEmitter.event().name("latestAlarm").data(eventData));
        } catch (Exception e) {
            AlarmController.sseEmitters.remove(memberId);
        }

        return count;
    }

    @Override
    public Long getCount(String memberId) {
        return alarmCustomRepository.getAlarmCount(memberId);
    }
}
