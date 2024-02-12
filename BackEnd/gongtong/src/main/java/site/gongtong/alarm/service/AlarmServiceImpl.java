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
import site.gongtong.member.repository.MemberRepository;

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
    private static Map<Integer, Integer> alarmCounts = new HashMap<>();

    @Override
    public Integer readAlarm(Integer id, Integer userNum) {
        Alarm alarm = alarmCustomRepository.findById(id);

        if (alarm == null) {
            return 1;
        }
        alarm.setIsRead(true);

        Alarm readAlarm = alarmRepository.save(alarm);

        if(readAlarm == null){
            return 2;
        }

        if (alarmCounts.containsKey(userNum)) {
            int currentCount = alarmCounts.get(userNum);

            if (currentCount > 0) {
                alarmCounts.put(userNum, currentCount - 1);
            }
        }

        SseEmitter sseEmitter = AlarmController.sseEmitters.get(userNum);

        try {
            sseEmitter.send(SseEmitter.event().name("alarmCount").data(alarmCounts.get(userNum)));
        } catch (IOException e) {
            return 3;
        }

        return 0;
    }

    @Override
    public SseEmitter subscribe(int userNum) {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);

        try {
            // SSE 연결
            // 첫 구독 시 이벤트를 보냄, 연결되었다는 응답으로 보내는 것, 안 보내면 503에러
            sseEmitter.send(SseEmitter.event().name("connect"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AlarmController.sseEmitters.put(userNum, sseEmitter);

        sseEmitter.onCompletion(() -> AlarmController.sseEmitters.remove(userNum));
        sseEmitter.onTimeout(() -> AlarmController.sseEmitters.remove(userNum));
        sseEmitter.onError((e) -> AlarmController.sseEmitters.remove(userNum));

        return sseEmitter;
    }

    @Override
    public List<Alarm> getAlarmList(int userNum) {
        return alarmCustomRepository.findAllByMember(userNum);
    }

    @Override
    public Integer getCount(int userNum) {
        return null;
    }

    @Override
    public Integer alarmMessage(int userNum) {
        Member member = memberCustomRepository.findMemberByNum(userNum);

        if (member == null) {
            return 1;       // 해당 유저는 없음
        }
        Alarm recieveAlarm = alarmCustomRepository.findFirstByRecieverOrderByCreatedAtDesc(userNum);

        if (recieveAlarm == null) {
            return 2;       // 알림이 1개도 없음
        }

        if (AlarmController.sseEmitters.containsKey(userNum)) {
            SseEmitter sseEmitter = AlarmController.sseEmitters.get(userNum);
            try {
                Map<String, String> eventData = new HashMap<>();
                eventData.put("alarm", "알림이 왔습니다.");
                eventData.put("content", recieveAlarm.getContent());
                eventData.put("createdAt", recieveAlarm.getCreatedAt().toString());
                eventData.put("link", recieveAlarm.getLink());
                sseEmitter.send(SseEmitter.event().name("addAlarm").data(eventData));
            } catch (Exception e) {
                AlarmController.sseEmitters.remove(userNum);
            }
        }

        return 0;
    }
}
