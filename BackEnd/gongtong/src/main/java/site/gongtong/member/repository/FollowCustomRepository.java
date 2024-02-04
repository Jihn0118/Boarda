package site.gongtong.member.repository;

import site.gongtong.member.model.Follow;
import site.gongtong.member.model.Member;

public interface FollowCustomRepository {

    int existRelation(int followerNum, int followingNum);

    Follow findBy2Nums(int myNum, int yourNum);
}
