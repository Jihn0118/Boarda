package site.gongtong.member.service;

import com.querydsl.core.Tuple;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.gongtong.alarm.model.Alarm;
import site.gongtong.alarm.repository.AlarmRepository;
import site.gongtong.alarm.service.AlarmService;
import site.gongtong.member.model.Follow;
import site.gongtong.member.model.Member;
import site.gongtong.member.repository.FollowRepository;
import site.gongtong.member.repository.MyPageCustomRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final AlarmService alarmService;
    private final FollowRepository followRepository;
    private final MyPageCustomRepository myPageRepository;
    private final AlarmRepository alarmRepository;

    @Override
    public Follow findBy2Nums(int myNum, int yourNum) {
        return followRepository.findBy2Nums(myNum, yourNum);
    }

    @Override
    public void deleteFollow(Follow wannaDeleteFollow) {
        followRepository.delete(wannaDeleteFollow);
    }

    @Override
    public List<Tuple> getFollowList(int myNum) {
        return followRepository.findAllByNum(myNum);

    }

    @Override
    public int doFollow(String myId, char flag, String yourNickname) {
        Member memMe = myPageRepository.findById(myId);
        Member memYou = myPageRepository.findByNickname(yourNickname);

        if (memMe == null || memYou == null || memMe == memYou) {
            System.out.println("nononono MEMBER FOLLOW");
            return 0;
        }
        try {
            if (followRepository.existRelation(memMe.getNum(), memYou.getNum()) >= 1) {
                System.out.println("ALREADY FOLLOW/BLOCK");
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }

        Follow newRelation;

        try {
            newRelation = followRepository.save(new Follow(memMe, flag, memYou));
        } catch (RuntimeException e) {
            System.out.println("팔로우 중 문제 생김");
            return 2;
        }

        Follow reverseRelation = followRepository.findBy2Nums(memYou.getNum(), memMe.getNum());
        if (newRelation.getFlag() == 'F') {
            if (reverseRelation != null) {
                Alarm alarm1 = Alarm.builder()
                        .link(null)
                        .isRead(false)
                        .member(memYou)
                        .content(memMe.getNickname() + "님과 당신이 맞팔 했습니다.")
                        .build();
                Alarm alarm = Alarm.builder()
                        .link(null)
                        .isRead(false)
                        .member(memMe)
                        .content(memYou.getNickname() + "님과 당신이 맞팔 했습니다.")
                        .build();
                alarmRepository.save(alarm);
                alarmRepository.save(alarm1);

                alarmService.alarmMessage(memMe.getId());
                alarmService.alarmMessage(memYou.getId());
            } else {
                Alarm alarm = Alarm.builder()
                        .link(null)
                        .isRead(false)
                        .member(memYou)
                        .content(memMe.getNickname() + "님이 당신을 팔로우 했습니다.")
                        .build();
                alarmRepository.save(alarm);
                alarmService.alarmMessage(memYou.getId());
            }
        }
        return 1;
    }

    @Override
    public List<Long> getFollowCount(String id) {
        List<Long> countList = new ArrayList<>();

        Long followerCount = followRepository.countFollower(id);
        Long followingCount = followRepository.countFollowing(id);

        if (followerCount == null) {
            countList.add(0L);
        } else {
            countList.add(followerCount);
        }
        if (followingCount == null) {
            countList.add(0L);
        } else {
            countList.add(followingCount);
        }

        return countList;
    }

}
