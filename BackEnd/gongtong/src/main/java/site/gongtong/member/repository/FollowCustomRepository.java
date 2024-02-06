package site.gongtong.member.repository;

import com.querydsl.core.Tuple;
import site.gongtong.member.model.Follow;
import site.gongtong.member.model.Member;

import java.util.List;

public interface FollowCustomRepository {

    int existRelation(int followerNum, int followingNum);

    Follow findBy2Nums(int myNum, int yourNum);

    List<Tuple> findAllByNum(int userNum);
}
