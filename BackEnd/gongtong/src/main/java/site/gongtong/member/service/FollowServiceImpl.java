package site.gongtong.member.service;

import com.querydsl.core.Tuple;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.gongtong.member.model.Follow;
import site.gongtong.member.model.Member;
import site.gongtong.member.repository.FollowRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;

    @Override
    public Follow save(Member memMe, char flag, Member memYou) {
        return followRepository.save(new Follow(memMe, flag, memYou));
    }

    @Override
    public int existRelation(int followerNum, int followingNum) {
        return followRepository.existRelation(followerNum, followingNum);
    }

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

}
