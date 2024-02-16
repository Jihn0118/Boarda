package site.gongtong.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.gongtong.cafe.model.Cafe;

import java.util.List;


public interface MapRepository extends JpaRepository<Cafe, Long> {
    List<Cafe> findByLocationContaining(String location);

    List<Cafe> findByLocationContainingAndBrand(String location, String brand);

    boolean existsByBranch(String branch);
};
