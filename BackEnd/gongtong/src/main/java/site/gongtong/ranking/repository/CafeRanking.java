package site.gongtong.ranking.repository;

import site.gongtong.cafe.model.Cafe;

public interface CafeRanking {
    Integer getCafeId();
    Long getTagCount();
    Cafe getCafe();
}
