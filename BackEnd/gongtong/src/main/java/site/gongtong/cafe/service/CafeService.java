package site.gongtong.cafe.service;

import site.gongtong.cafe.model.Cafe;

import java.util.List;

public interface CafeService {
    List<Cafe> getCafeList(String location, String brand);

    Cafe getCafeInfo(Integer cafeId);
}
