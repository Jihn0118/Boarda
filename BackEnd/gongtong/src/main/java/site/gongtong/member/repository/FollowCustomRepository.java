package site.gongtong.member.repository;

import com.querydsl.core.Tuple;
import site.gongtong.member.model.Follow;

import java.util.List;

public interface FollowCustomRepository {

    int existRelation(int followerNum, int followingNum);

    Follow findBy2Nums(int myNum, int yourNum);

    List<Tuple> findAllByNum(int userNum);

    Long countFollower(String id);

    Long countFollowing(String id);
}
