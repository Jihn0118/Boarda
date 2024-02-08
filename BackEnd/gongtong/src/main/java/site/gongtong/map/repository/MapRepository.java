package site.gongtong.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.map.model.CafeMap;

import java.util.List;

//public interface MapRepository extends JpaRepository<CafeMap, Long> {};

public interface MapRepository extends JpaRepository<CafeMap, Long> {
    List<CafeMap> findByLocationContaining(String location);
    List<CafeMap> findByLocationContainingAndBrand(String location, String brand);

    boolean existsByBranch(String branch);
};
