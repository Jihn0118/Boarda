package site.gongtong.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.gongtong.member.model.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer>, FollowCustomRepository {

}
