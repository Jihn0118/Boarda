package site.gongtong.member.service;

import site.gongtong.member.model.Follow;
import site.gongtong.member.model.Member;

public interface FollowService {
    Follow save(Member memMe, char flag, Member memYou);

    int existRelation(int followerNum, int followingNum);

}
