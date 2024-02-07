package site.gongtong.member.service;

import com.querydsl.core.Tuple;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.gongtong.member.model.Follow;
import site.gongtong.member.model.Member;
import site.gongtong.member.repository.FollowCustomRepository;
import site.gongtong.member.repository.FollowRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;
    private final FollowCustomRepository followCustomRepository;

    @Override
    public Follow save(Member memMe, char flag, Member memYou) {
        Follow follow = Follow.builder()
                .flag(flag)
                .following(memYou)
                .follower(memMe)
                .build();
        return followRepository.save(follow);
    }

    @Override
    public Integer existRelation(int followerNum, int followingNum) {
        return followCustomRepository.existRelation(followerNum, followingNum);
    }

    @Override
    public Follow findBy2Nums(int myNum, int yourNum) {
        return followCustomRepository.findBy2Nums(myNum, yourNum);
    }

    @Override
    public void deleteFollow(Follow wannaDeleteFollow) {
        followRepository.delete(wannaDeleteFollow);
    }

    @Override

    public List<Tuple> getFollowList(int myNum) {
        return followCustomRepository.findAllByNum(myNum);

    }

}
