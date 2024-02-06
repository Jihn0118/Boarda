package site.gongtong.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.gongtong.cafe.model.Cafe;
import site.gongtong.map.model.CafeMap;

public interface MapRepository extends JpaRepository<CafeMap, Long> {};

