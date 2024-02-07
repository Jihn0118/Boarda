package site.gongtong.member.service;

import com.querydsl.core.Tuple;
import site.gongtong.member.model.Follow;
import site.gongtong.member.model.Member;

import java.util.List;

public interface FollowService {
    Follow save(Member memMe, char flag, Member memYou);

    Integer existRelation(int followerNum, int followingNum);

    Follow findBy2Nums(int myNum, int yourNum);

    void deleteFollow(Follow wannaDeleteFollow);

    List<Tuple> getFollowList(int myNum);

}
