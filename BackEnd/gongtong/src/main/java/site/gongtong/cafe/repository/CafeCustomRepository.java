package site.gongtong.cafe.repository;

import site.gongtong.cafe.model.Cafe;

import java.util.List;

public interface CafeCustomRepository {
    List<Cafe> findWithConditions(String location, String brand);
    Cafe findCafeDetail(Integer cafeId);
}
