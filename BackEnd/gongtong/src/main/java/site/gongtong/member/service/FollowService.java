package site.gongtong.member.service;

import com.querydsl.core.Tuple;
import site.gongtong.member.model.Follow;

import java.util.List;

public interface FollowService {

    Follow findBy2Nums(int myNum, int yourNum);

    void deleteFollow(Follow wannaDeleteFollow);

    List<Tuple> getFollowList(int myNum);

    int doFollow(String myId, char flag, String yourNickname);

    List<Long> getFollowCount(String id);
}
