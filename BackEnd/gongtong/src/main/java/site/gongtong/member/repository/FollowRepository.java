package site.gongtong.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.gongtong.member.model.Follow;

public interface FollowRepository extends JpaRepository<Follow,Integer>, FollowCustomRepository {

}
