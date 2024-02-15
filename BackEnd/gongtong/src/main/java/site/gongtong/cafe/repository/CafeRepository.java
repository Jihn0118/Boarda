package site.gongtong.cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.gongtong.cafe.model.Cafe;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Integer> {
}
