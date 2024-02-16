package site.gongtong.moim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.gongtong.moim.model.MoimMember;

public interface MoimMemberRepository extends JpaRepository<MoimMember, Integer> {
}
