package site.gongtong.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.gongtong.member.model.Member;

import java.util.Optional;

public interface MyPageRepository  extends JpaRepository<Member,Integer>, MyPageCustomRepository {

}
