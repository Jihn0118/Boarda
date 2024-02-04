package site.gongtong.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.gongtong.member.model.Follow;
import site.gongtong.member.model.Member;
import site.gongtong.member.repository.FollowRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    @Autowired
    private final FollowRepository followRepository;

    @Override
    public Follow save(Member memMe, char flag, Member memYou) {
        return followRepository.save(new Follow(memMe, flag, memYou));
    }

    @Override
    public int existRelation(int followerNum, int followingNum) {
        return followRepository.existRelation(followerNum, followingNum);
    }

}
