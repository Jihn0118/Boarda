package site.gongtong.moim.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.gongtong.alarm.model.Alarm;
import site.gongtong.alarm.repository.AlarmRepository;
import site.gongtong.alarm.service.AlarmService;
import site.gongtong.member.model.Member;
import site.gongtong.member.repository.MemberCustomRepository;
import site.gongtong.moim.model.Moim;
import site.gongtong.moim.model.MoimGroup;
import site.gongtong.moim.model.MoimMember;
import site.gongtong.moim.repository.MoimCustomRepository;
import site.gongtong.moim.repository.MoimMemberCustomRepository;
import site.gongtong.moim.repository.MoimMemberRepository;
import site.gongtong.moim.repository.MoimRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MoimServiceImpl implements MoimService {
    private final MoimRepository moimRepository;
    private final MoimMemberRepository moimMemberRepository;
    private final MoimCustomRepository moimCustomRepository;
    private final MoimMemberCustomRepository moimMemberCustomRepository;
    private final MemberCustomRepository memberCustomRepository;
    private final AlarmRepository alarmRepository;
    private final AlarmService alarmService;

    @Override
    public List<Moim> getDeadlineList() {
        return moimCustomRepository.findAllDeadLine();
    }

    @Override
    public Integer checkRoom(String memberId) {
        return moimMemberCustomRepository.countMoimsByMemberIdAndStatus(memberId);
    }

    @Override
    public Integer createRoom(Moim moim, String memberId) {
        Member member = memberCustomRepository.findMemberById(memberId);

        if (member == null) {
            return 1;
        }

        moim.setLeaderNickname(member.getNickname());

        Moim saveMoim = moimRepository.save(moim);

        if (saveMoim != null) {
            MoimMember saveMoimMember = MoimMember.builder()
                    .member(member)
                    .moim(saveMoim)
                    .build();

            moimMemberRepository.save(saveMoimMember);

            Alarm alarm = Alarm.builder()
                    .link("방금 만든 방의 상세 페이지 화면 링크")
                    .member(member)
                    .isRead(false)
                    .content("모임 방 생성 완료!")
                    .build();

            alarmRepository.save(alarm);

            alarmService.alarmMessage(memberId);

            return 0;
        } else {
            System.out.println("에러");
            return 2;
        }
    }

    @Override
    public Integer joinRoom(int moimId, String memberId) {
        boolean isFull = false;

        int count = moimMemberCustomRepository.countMoimMemberByMoimId(moimId);

        Moim moim = moimCustomRepository.findById(moimId);

        Member addMember = memberCustomRepository.findById(memberId);

        if (moim == null) {
            return 1;
        } else if (addMember == null) {
            return 2;
        }

        MoimMember existedMoimMember = moimMemberCustomRepository.findMoimMemberByMoimAndMember(moim, addMember);

        if (count == moim.getNumber()) {
            return 3;
        } else if (existedMoimMember != null) {// 이미 모임에 참여한 사람인 지 확인
            return 4;
        } else if (count + 1 == moim.getNumber()) {
            moim.setStatus('S');
            isFull = true;
        }
        moim.setCurrentNumber(count + 1);
        moimRepository.save(moim);

        MoimMember addMoimMember = MoimMember.builder()
                .moim(moim)
                .member(addMember)
                .build();

        moimMemberRepository.save(addMoimMember);

        if (isFull) {     // 모임이 꽉 찼으니 모임 멤버들에게 알림 생성
            List<MoimMember> moimMemberList = moimMemberCustomRepository.findMoimMembersByMoim(moim);
            List<Alarm> alarms = new ArrayList<>();
            List<String> memberIdList = new ArrayList<>();

            for (MoimMember mm : moimMemberList) {
                Member member = mm.getMember();
                String memId = mm.getMember().getId();
                memberIdList.add(memId);

                Alarm addAlarm = Alarm.builder()
                        .link("다 모인 방의 상세 페이지 화면 링크")
                        .member(member)
                        .isRead(false)
                        .content("참여중인 보드게임에 사람들이 다 모였어요!")
                        .build();

                alarms.add(addAlarm);

                // SSE 알림
                alarmService.alarmMessage(member.getId());
            }
            alarmRepository.saveAll(alarms);

            for (String memId : memberIdList) {
                alarmService.alarmMessage(memId);
            }
        }
        return 0;
    }

    @Override
    public List<Moim> getMyMoimList(String memberId) {
        return moimCustomRepository.findMoimListByMemberId(memberId);
    }

    @Override
    public Moim getMyMoim(String memberId) {
        return moimCustomRepository.findMoimByMemberId(memberId);
    }

    @Override
    public int inviteFriend(String memberId, String friendId, int moimId) {
        Member friend = memberCustomRepository.findById(friendId);
        Moim moim = moimCustomRepository.findById(moimId);

        if (friend != null && moim != null) {
            Alarm addAlarm = Alarm.builder()
                    .content("")
                    .member(friend)
                    .isRead(false)
                    .content(memberId + "님이 " + moim.getTitle() + " 방에 초대하셨습니다.")
                    .link("no link")
                    .build();

            alarmRepository.save(addAlarm);

            alarmService.alarmMessage(friendId);

            return 0;
        } else {
            return 1;       // 프렌드나 모임이 널임
        }

    }

    @Override
    public List<Moim> getSortedMoimList(String location, int sorting) {
        List<Moim> list;


        if (sorting == 2) {          // 마감임박순 정렬
            list = moimCustomRepository.findByLocationAndStatusOrderByCount(location);
        } else if (sorting == 3) {   // 모집일시 정렬
            list = moimCustomRepository.findByLocationAndStatusOrderByDatetime(location);
        } else {                    // 최신순 정렬
            list = moimCustomRepository.findByLocationAndStatusOrderByIdDesc(location);
        }

        return list;
    }

    @Override
    public long exitRoom(String memberId, int moimId) {
        long result = moimMemberCustomRepository.deleteMoimMember(memberId, moimId);
        if (result != 1) {
            return -1;  // 모임 멤버 안 지워짐
        }

        Moim moim = moimCustomRepository.findById(moimId);

        long result2 = 0L;
        if (moim.getCurrentNumber() > 1) {
            result2 = moimCustomRepository.minusCurrentNumber(memberId, moimId, moim.getCurrentNumber() - 1);
        } else if (moim.getCurrentNumber() == 1) {
            result2 = moimCustomRepository.deleteMoim(moimId);
        }

        if (result2 != 1) {
            return -2;  // 모임 인원수 -1 이 안되거나, 모임 삭제가 안되거나
        }
        return result;
    }

    @Override
    public List<MoimGroup> getMyMoimGroupList(String memberId) {
        return moimMemberCustomRepository.findMoimGroupByMemberId(memberId);
    }


}
