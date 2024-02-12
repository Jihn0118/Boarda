package site.gongtong.member.service;

import com.querydsl.core.Tuple;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.gongtong.member.model.Follow;
import site.gongtong.member.model.Member;
import site.gongtong.member.repository.FollowRepository;
import site.gongtong.member.repository.MyPageRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;
    private final MyPageRepository myPageRepository;

//    @Override
//    public Follow save(Member memMe, char flag, Member memYou) {
//        return followRepository.save(new Follow(memMe, flag, memYou));
//    }

//    @Override
//    public int existRelation(int followerNum, int followingNum) {
//        return followRepository.existRelation(followerNum, followingNum);
//    }

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

        if(memMe == null || memYou == null || memMe == memYou) {
            System.out.println("nononono MEMBER FOLLOW");
            return 0;
        }
        try {
            if( followRepository.existRelation(memMe.getNum(), memYou.getNum()) >= 1) {
                System.out.println("ALREADY FOLLOW/BLOCK");
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }

        Follow newRelation = followRepository.save(new Follow(memMe, flag, memYou));
        if (newRelation == null){
            return 2;
        }

        return 1;
    }

//    @Override
//    public int deleteFollow(String myId, String followId) {
//        return 0;
//    }

}
