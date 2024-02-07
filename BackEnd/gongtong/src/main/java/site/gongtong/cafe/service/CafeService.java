package site.gongtong.cafe.service;

import site.gongtong.cafe.model.Cafe;
import site.gongtong.cafe.model.CafeDetailDto;

import java.util.List;

public interface CafeService {
    List<Cafe> getCafeList(String location, String brand);

    CafeDetailDto getCafeInfo(Integer cafeId);
}
