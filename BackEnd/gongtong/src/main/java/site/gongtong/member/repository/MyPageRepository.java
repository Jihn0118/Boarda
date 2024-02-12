package site.gongtong.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.gongtong.member.model.Member;

import java.util.Optional;

@Repository
public interface MyPageRepository  extends JpaRepository<Member,Integer>, MyPageCustomRepository {

}
