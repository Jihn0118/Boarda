package site.gongtong.moim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.gongtong.moim.model.Moim;

@Repository
public interface MoimRepository extends JpaRepository<Moim, Integer> {
}
